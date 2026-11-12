/**
 * @author jawc
 */
package br.com.jawc.logistics.order_service.dto;

import java.math.BigDecimal;

public record OrdersPerDayDTO(
        String date,
        Long totalOrders,
        BigDecimal totalRevenue
) {
}
