/**
 * @author jawc
 */
package br.com.jawc.logistics.delivery_service.exception;

import org.springframework.cloud.client.circuitbreaker.httpservice.HttpServiceFallback;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//AQUI AVISANDO PARA O SPRING RETORNAR 404 QUANDO ESSA EXCEPTION ESTOURAR
@ResponseStatus(HttpStatus.NOT_FOUND)

public class CourierNotFoundException extends RuntimeException {
    public CourierNotFoundException(String message) {
        super(message);
    }
}
