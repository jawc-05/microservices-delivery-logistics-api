/**
 * @author jawc
 */
package br.com.jawc.logistics.auth_service.service;

import br.com.jawc.logistics.auth_service.domain.User;
import br.com.jawc.logistics.auth_service.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findBydEmail(email);
    }
    
    public User createUser(User user) {
        //TODO: Implementar BCryptPasswordEncoder do Spring Security antes de salvar
        return userRepository.save(user);
    }
    
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
