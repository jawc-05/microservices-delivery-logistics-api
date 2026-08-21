/**
 * @author jawc
 */
package br.com.jawc.logistics.auth_service.repository;

import br.com.jawc.logistics.auth_service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
