/**
 * @author jawc
 */
package br.com.jawc.logistics.auth_service.service;

import br.com.jawc.logistics.auth_service.domain.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

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
                    //DEFININDO POR QUANTO TEMPO O TOKEN DURA
                    .withExpiresAt(Instant.now().plusSeconds(3600))
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar JWT", e);
        }
    }

    public String validateToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-service")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e){
            throw new RuntimeException("", e);
        }
    }
}
