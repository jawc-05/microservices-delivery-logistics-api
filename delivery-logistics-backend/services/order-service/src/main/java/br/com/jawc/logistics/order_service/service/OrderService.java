/**
 * @author jawc
 */
package br.com.jawc.logistics.order_service.service;

import br.com.jawc.logistics.order_service.domain.Order;
import br.com.jawc.logistics.order_service.domain.OrderStatus;
import br.com.jawc.logistics.order_service.dto.CourierResponseDTO;
import br.com.jawc.logistics.order_service.dto.OrdersPerDayDTO;
import br.com.jawc.logistics.order_service.feign.DeliveryClient;
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

        return orderRepository.save(order);
    }

    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public List<OrdersPerDayDTO> getOrdersPerDayReport() {
        return orderReportRepository.getOrdersPerDayReport();
    }
}
