/**
 * @author jawc
 */
package br.com.jawc.logistics.auth_service.config;

import br.com.jawc.logistics.auth_service.repository.IUserRepository;
import br.com.jawc.logistics.auth_service.service.TokenService;
import br.com.jawc.logistics.auth_service.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if (token != null) {
            // TODO 1: Chamar o tokenService.validateToken() passando a variável token
            String emailToken = tokenService.validateToken(token);

            // TODO 2: Se o retorno não for vazio, usar o userRepository para buscar o usuário pelo e-mail
           if (!emailToken.isEmpty()) {
               //USANDO REPOSITORY ao invés de SERVICE para não dar depêndencia circular
               var user = userRepository.findByEmail(emailToken).orElse(null);
               // TODO 3: Se o usuário existir, forçar a autenticação dele no contexto do Spring Security
            if (user != null){
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
           }
        }filterChain.doFilter(request, response);


    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
