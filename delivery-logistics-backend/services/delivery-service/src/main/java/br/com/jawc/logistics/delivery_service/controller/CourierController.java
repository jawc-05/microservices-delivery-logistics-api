/**
 * @author jawc
 */
package br.com.jawc.logistics.delivery_service.controller;

import br.com.jawc.logistics.delivery_service.domain.Courier;
import br.com.jawc.logistics.delivery_service.dto.CourierRequestDTO;
import br.com.jawc.logistics.delivery_service.dto.CourierResponseDTO;
import br.com.jawc.logistics.delivery_service.repository.ICourierRepository;
import br.com.jawc.logistics.delivery_service.service.CourierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
@Tag(name = "Courier", description = "Endpoints for Courier management")
public class CourierController {

    private final CourierService courierService;


}
