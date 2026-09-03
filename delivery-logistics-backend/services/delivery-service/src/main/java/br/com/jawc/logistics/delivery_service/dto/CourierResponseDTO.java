/**
 * @author jawc
 */
package br.com.jawc.logistics.delivery_service.dto;

import br.com.jawc.logistics.delivery_service.domain.CourierStatus;

public record CourierResponseDTO(
        Long id,
        String name,
        String phone,
        CourierStatus status
) {
}
