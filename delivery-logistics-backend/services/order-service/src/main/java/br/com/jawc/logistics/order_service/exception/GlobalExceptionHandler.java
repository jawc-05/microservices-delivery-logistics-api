/**
 * @author jawc
 */
package br.com.jawc.logistics.order_service.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<StandardError> handleOrderNotFoundException(OrderNotFoundException e, HttpServletRequest request) {

        StandardError error = new StandardError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                e.getMessage(),
                request.getRequestURI()
                );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
