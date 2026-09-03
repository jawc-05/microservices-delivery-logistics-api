/**
 * @author jawc
 */
package br.com.jawc.logistics.delivery_service.service;

import br.com.jawc.logistics.delivery_service.domain.Courier;
import br.com.jawc.logistics.delivery_service.repository.ICourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierService {
    private final ICourierRepository courierRepository;

    public Courier createCourier(Courier courier){
        return courierRepository.save(courier);
    }
}
