/**
 * @author jawc
 */
package br.com.jawc.logistics.order_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", url = "http://localhost:8084")
public interface NotificationClient {

    @PostMapping("/api/notifications")
    void sendNotification(@RequestBody NotificationRequestDTO request);
}
