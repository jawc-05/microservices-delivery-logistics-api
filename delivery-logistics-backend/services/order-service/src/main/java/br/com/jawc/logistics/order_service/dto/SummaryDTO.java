/**
 * @author jawc
 */
package br.com.jawc.logistics.order_service.dto;

public record SummaryDTO(
        Integer totalPending,
        Integer totalConfirmed,
        Integer totalShipped,
        Integer totalDelivered,
        Integer totalCancelled
) {
}
