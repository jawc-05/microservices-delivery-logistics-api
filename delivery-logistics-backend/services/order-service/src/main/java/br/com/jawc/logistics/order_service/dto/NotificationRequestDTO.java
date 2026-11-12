/**
 * @author jawc
 */
package br.com.jawc.logistics.order_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NotificationRequestDTO(
        @NotNull(message = "Order ID cannot be NULL")
        Long orderId,

        @NotBlank(message = "Message cannot be BLANK")
        String message
) {
}
