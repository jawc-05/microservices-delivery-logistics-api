/**
 * @author jawc
 */
package br.com.jawc.logistics.order_service.dto;

import br.com.jawc.logistics.order_service.domain.OrderStatus;

public record OrderStatusRequestDTO(
        OrderStatus status
) {
}
