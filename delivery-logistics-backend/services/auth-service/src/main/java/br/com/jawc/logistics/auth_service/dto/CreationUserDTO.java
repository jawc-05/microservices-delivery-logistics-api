/**
 * @author jawc
 */
package br.com.jawc.logistics.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreationUserDTO(
        @Email @NotBlank String email,
        @NotBlank String password,
        @NotBlank @NotNull String role
) {
}
