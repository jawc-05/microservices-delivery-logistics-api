/**
 * @author jawc
 */
package br.com.jawc.logistics.order_service.service;

import br.com.jawc.logistics.order_service.domain.Order;
import br.com.jawc.logistics.order_service.domain.OrderStatus;
import br.com.jawc.logistics.order_service.dto.CourierResponseDTO;
import br.com.jawc.logistics.order_service.dto.NotificationRequestDTO;
import br.com.jawc.logistics.order_service.dto.OrdersPerDayDTO;
import br.com.jawc.logistics.order_service.exception.OrderNotFoundException;
import br.com.jawc.logistics.order_service.feign.DeliveryClient;
import br.com.jawc.logistics.order_service.feign.NotificationClient;
import br.com.jawc.logistics.order_service.repository.IOrderRepository;
import br.com.jawc.logistics.order_service.repository.OrderReportRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final IOrderRepository orderRepository;
    private final OrderReportRepository orderReportRepository;
    private final DeliveryClient deliveryClient;
    private final NotificationClient notificationClient;

    public Order createOrder(Order order) {
        try {
            //TENTA BUSCAR SE TEM UM ENTREGADOR DISPONIVEL NA HRA
            CourierResponseDTO courier = deliveryClient.getAvailableCourier();

            //SE TIVER JA ADD
            order.setCourierId(courier.id());
            order.setStatus(OrderStatus.CONFIRMED);
        } catch (FeignException.NotFound e){
            // O Delivery Service respondeu 404 (Nenhum entregador disponível)
            // Engolimos o erro para não perder a venda da transportadora!
            order.setCourierId(null);
            order.setStatus(OrderStatus.PENDING);
        } catch (FeignException e){
            // Se o Delivery Service estiver FORA DO AR (ex: 503, 500), cai aqui.
            // Também salvamos o pedido para garantir o negócio!
            order.setCourierId(null);
            order.setStatus(OrderStatus.PENDING);
        }
        Order savedOrder = orderRepository.save(order);

        try {
            // 1. Instancia o DTO corretamente
            var notificationRequest = new NotificationRequestDTO(
                    savedOrder.getId(),
                    "Pedido Criado com status: " + savedOrder.getStatus()
            );
            // 2. Envia para o MongoDB
            notificationClient.sendNotification(notificationRequest);

        } catch (Exception e) {
            // 3. Se o notification-service estiver fora do ar, engolimos o erro!
            // O pedido JÁ FOI SALVO no PostgreSQL na linha de cima. O negócio está garantido.
            System.err.println("Aviso: Falha ao enviar log para o Notification Service. Motivo: " + e.getMessage());
        }

        return savedOrder;
    }

    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public Order updateStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));

        order.setStatus(newStatus);

        return orderRepository.save(order);
    }

    public List<OrdersPerDayDTO> getOrdersPerDayReport() {
        return orderReportRepository.getOrdersPerDayReport();
    }
}
