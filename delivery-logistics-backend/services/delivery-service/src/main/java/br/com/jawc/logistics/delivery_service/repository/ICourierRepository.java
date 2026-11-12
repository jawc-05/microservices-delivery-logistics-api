/**
 * @author jawc
 */
package br.com.jawc.logistics.delivery_service.repository;

import br.com.jawc.logistics.delivery_service.domain.Courier;
import br.com.jawc.logistics.delivery_service.domain.CourierStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICourierRepository extends JpaRepository<Courier, Long> {

    Optional<Courier> findFirstByStatus(CourierStatus status);
}
