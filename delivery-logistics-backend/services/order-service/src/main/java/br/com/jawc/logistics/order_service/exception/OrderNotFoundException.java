/**
 * @author jawc
 */
package br.com.jawc.logistics.order_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//AQUI AVISANDO PARA O SPRING RETORNAR 404 QUANDO ESSA EXCEPTION ESTOURAR
@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
