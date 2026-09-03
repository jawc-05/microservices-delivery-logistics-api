/**
 * @author jawc
 */
package br.com.jawc.logistics.order_service.dto;

import br.com.jawc.logistics.order_service.domain.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponseDTO(
        Long id,
        String recipientEmail,
        String recipientName,
        BigDecimal totalAmount,
        OrderStatus status,
        LocalDateTime createdAt,
        Long courierId
) {
}
