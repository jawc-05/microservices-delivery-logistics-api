/**
 * @author jawc
 */
package br.com.jawc.logistics.order_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "delivery-service", url = "http://localhost:8083/api/delivery")
public interface DeliveryClient {

    @GetMapping("/available")
    Object getAvailableCourier();
}
