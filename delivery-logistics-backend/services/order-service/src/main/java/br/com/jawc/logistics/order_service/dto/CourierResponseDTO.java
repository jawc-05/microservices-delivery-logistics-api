/**
 * @author jawc
 */
package br.com.jawc.logistics.order_service.dto;

public record CourierResponseDTO(
        Long id,
        String name,
        String status,
        String phone
) {
}
