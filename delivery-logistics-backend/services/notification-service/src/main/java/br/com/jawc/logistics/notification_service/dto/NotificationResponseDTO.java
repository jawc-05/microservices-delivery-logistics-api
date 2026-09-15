/**
 * @author jawc
 */
package br.com.jawc.logistics.notification_service.dto;

import java.time.Instant;

public record NotificationResponseDTO(
        String id,
        Long orderId,
        String message,
        Instant createdAt
) {
}
