/**
 * @author jawc
 */
package br.com.jawc.logistics.order_service.repository;

import br.com.jawc.logistics.order_service.dto.OrdersPerDayDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderReportRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<OrdersPerDayDTO> getOrdersPerDayReport() {
        //AQUI iremos utilizar um SQL nativo, para ser otimizado e direto no POSTGRESQL

        String sql = """
                SELECT DATE(created_at) as order_date,
                       COUNT(id) as total_orders,
                       SUM(total_amount) as total_revenue
                FROM tb_orders
                GROUP BY DATE(created_at)
                ORDER BY order_date DESC
                """;

        //EXECUTA A QUERY E MAPEA O RESULTADO PARA O DTO
        return jdbcTemplate.query(sql, (rs, rowNum) -> new OrdersPerDayDTO(
                rs.getString("order_date"),
                rs.getLong("total_orders"),
                rs.getBigDecimal("total_revenue")
        ));
    }
}
