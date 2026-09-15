/**
 * @author jawc
 */
package br.com.jawc.logistics.notification_service.repository;

import br.com.jawc.logistics.notification_service.domain.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {

    List<Notification> findOrderById(Long orderId);
}
