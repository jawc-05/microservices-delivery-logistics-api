/**
 * @author jawc
 */
package br.com.jawc.logistics.auth_service.controller;

import br.com.jawc.logistics.auth_service.domain.User;
import br.com.jawc.logistics.auth_service.dto.LoginRequestDTO;
import br.com.jawc.logistics.auth_service.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO loginDTO) {
        //ENCAPSULA O EMAIL E SENHA RECEBIDOS PELO PADRÃO DO SPRING SECURITY
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password());

        //o AuthenticationManager valida a senha real com o hash
        var auth = this.authenticationManager.authenticate(usernamePassword);

        //Com o usuário validado ele gera o token jwt
        var token = tokenService.generateToken((User)  auth.getPrincipal());

        return ResponseEntity.ok(token);

    }

}
