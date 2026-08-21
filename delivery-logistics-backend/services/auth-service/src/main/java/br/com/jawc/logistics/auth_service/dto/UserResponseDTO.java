/**
 * @author jawc
 */
package br.com.jawc.logistics.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record UserResponseDTO(
        String email,
        String role,
        LocalDateTime createdAt
) {
}
