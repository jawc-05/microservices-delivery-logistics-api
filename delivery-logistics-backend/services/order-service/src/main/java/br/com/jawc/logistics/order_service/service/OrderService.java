/**
 * @author jawc
 */
package br.com.jawc.logistics.order_service.service;

import br.com.jawc.logistics.order_service.domain.Order;
import br.com.jawc.logistics.order_service.dto.OrdersPerDayDTO;
import br.com.jawc.logistics.order_service.repository.IOrderRepository;
import br.com.jawc.logistics.order_service.repository.OrderReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final IOrderRepository orderRepository;
    private final OrderReportRepository orderReportRepository;

    public Order createOrder(Order order) {
        //Here i will need to update with some "filter" and so on
        return orderRepository.save(order);
    }

    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public List<OrdersPerDayDTO> getOrdersPerDayReport() {
        return orderReportRepository.getOrdersPerDayReport();
    }
}
