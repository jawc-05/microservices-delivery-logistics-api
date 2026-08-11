/**
 * @author jawc
 */
package br.com.jawc.logistics.order_service.repository;

import br.com.jawc.logistics.order_service.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {

}
