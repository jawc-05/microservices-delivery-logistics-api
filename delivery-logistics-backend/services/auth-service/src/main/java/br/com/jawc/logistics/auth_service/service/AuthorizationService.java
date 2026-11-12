/**
 * @author jawc
 */
package br.com.jawc.logistics.auth_service.service;

import br.com.jawc.logistics.auth_service.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final IUserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // O Spring Security chama este method passando o e-mail que veio no JSON de login
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }
}
