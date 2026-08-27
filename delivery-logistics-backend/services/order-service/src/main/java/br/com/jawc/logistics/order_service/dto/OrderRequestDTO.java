/**
 * @author jawc
 */
package br.com.jawc.logistics.order_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderRequestDTO(
        @NotBlank(message = "the customer name is needed")
        String customerName,

        @NotBlank(message = "the customer email is needed")
        @Email
        String customerEmail,

        @NotNull(message = "the amount of the order is needed")
        @Positive(message = "the value needs to be higher than zero")
        BigDecimal totalAmount

) {
}
