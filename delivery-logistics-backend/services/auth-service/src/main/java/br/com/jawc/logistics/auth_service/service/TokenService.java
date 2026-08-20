/**
 * @author jawc
 */
package br.com.jawc.logistics.auth_service.service;

import br.com.jawc.logistics.auth_service.domain.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try{
            // 1. Define o algoritmo e passa a chave secreta
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 2. Constrói e assina o token
            return JWT.create()
                    .withIssuer("auth-service")//Identifica qm emitiu
                    .withSubject(user.getEmail())//Salva o email dentro do token
            //TODO: Precisa ser definido quando esse token expira
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar JWT", e);
        }
    }
}
