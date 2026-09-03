/**
 * @author jawc
 */
package br.com.jawc.logistics.delivery_service.exception;

import java.time.Instant;

public record StandardError(
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path
) {
}
