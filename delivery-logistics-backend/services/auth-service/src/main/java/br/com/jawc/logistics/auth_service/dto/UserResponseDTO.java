/**
 * @author jawc
 */
package br.com.jawc.logistics.auth_service.dto;

import br.com.jawc.logistics.auth_service.domain.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String email,
        UserRole role,
        LocalDateTime createdAt
) {
}
