/**
 * @author jawc
 */
package br.com.jawc.logistics.delivery_service.repository;

import br.com.jawc.logistics.delivery_service.domain.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourierRepository extends JpaRepository<Courier, Long> {
}
