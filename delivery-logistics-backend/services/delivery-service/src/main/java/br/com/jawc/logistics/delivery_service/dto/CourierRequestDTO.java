/**
 * @author jawc
 */
package br.com.jawc.logistics.delivery_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record CourierRequestDTO(
        @NotBlank(message = "the courier name is needed")
        String name,

        @NotBlank(message = "the courier email is needed")
        @Email
        String email,

        @NotBlank(message = "the courier phone number is needed")
        String phone,

        @NotBlank(message = "the courier document is needed")
        String document
) {
}
