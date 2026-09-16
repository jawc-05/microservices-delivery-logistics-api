/**
 * @author jawc
 */
package br.com.jawc.logistics.order_service.repository;

import br.com.jawc.logistics.order_service.dto.SummaryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderAnalyticsRepository {
    private final JdbcTemplate jdbcTemplate;

    public SummaryDTO getOrderSummary() {
        String sql = """
        SELECT 
            COUNT(CASE WHEN status = 'PENDING' THEN 1 END) AS totalPending,
            COUNT(CASE WHEN status = 'CONFIRMED' THEN 1 END) AS totalConfirmed,
            COUNT(CASE WHEN status = 'SHIPPED' THEN 1 END) AS totalShipped,
            COUNT(CASE WHEN status = 'DELIVERED' THEN 1 END) AS totalDelivered,
            COUNT(CASE WHEN status = 'CANCELLED' THEN 1 END) AS totalCancelled
        FROM orders
    """;

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            return  new SummaryDTO(
                    rs.getInt("totalPending"),
                    rs.getInt("totalConfirmed"),
                    rs.getInt("totalShipped"),
                    rs.getInt("totalDelivered"),
                    rs.getInt("totalCancelled")
            );
        });
    }

}
