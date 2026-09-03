/**
 * @author jawc
 */
package br.com.jawc.logistics.delivery_service.exception;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //AVISAMOS QUE O method SO VAI AGIR QUANDO ESTOURAR A AÇAO
    @ExceptionHandler(CourierNotFoundException.class)
    public ResponseEntity<StandardError> handleCourierNotFound(CourierNotFoundException e, HttpServletRequest request){

        StandardError error = new StandardError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                e.getMessage(), //MINHA MENSAGEM SEGURA ENTRANDO
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
