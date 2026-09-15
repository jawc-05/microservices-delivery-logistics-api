/**
 * @author jawc
 */
package br.com.jawc.logistics.notification_service.service;

import br.com.jawc.logistics.notification_service.domain.Notification;
import br.com.jawc.logistics.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public Notification createNotification(Notification notification) {
        //Pegar a data e hora de criaçao automaticamente
        notification.setCreatedAt(Instant.now());
        //return normal
        return notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsByOrderId(Long orderId) {
        return notificationRepository.findOrderById(orderId);
    }
}
