/**
 * @author jawc
 */
package br.com.jawc.logistics.delivery_service.controller;

import br.com.jawc.logistics.delivery_service.domain.Courier;
import br.com.jawc.logistics.delivery_service.dto.CourierRequestDTO;
import br.com.jawc.logistics.delivery_service.dto.CourierResponseDTO;
import br.com.jawc.logistics.delivery_service.service.CourierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
@Tag(name = "Courier", description = "Endpoints for Courier management")
public class CourierController {

    private final CourierService courierService;

    @GetMapping
    @Operation(summary = "Get all Couriers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the paginated list of couriers"),
            @ApiResponse(responseCode = "400", description = "syntax error or bad request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "BAD_REQUEST"))),
            @ApiResponse(responseCode = "500", description = "An exception was made",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "INTERNAL_SERVER_ERROR"))),
    })
    public ResponseEntity<Page<CourierResponseDTO>> searchCouriers(Pageable pageable) {
        //ACHANDO OS COURIERS
        Page<Courier> couriersPage = courierService.getAllCouriers(pageable);

        //CRIANDO DTOPAGE
        Page<CourierResponseDTO> dtoPage = couriersPage.map(courier -> new CourierResponseDTO(
                courier.getId(),
                courier.getName(),
                courier.getPhone(),
                courier.getStatus()
        ));

        return ResponseEntity.status(HttpStatus.OK).body(dtoPage);
    }

    @PostMapping
    @Operation(summary = "Create a COurier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The courier was created"),
            @ApiResponse(responseCode = "400", description = "Validation error or duplicate key")
    })
    public ResponseEntity<CourierResponseDTO> createCourier(@RequestBody @Valid CourierRequestDTO request){
        //TRANSFORMANDO DTO DE ENTRADA NA ENTIDADE QUE VAI AO BANCO

        Courier newCourier = new Courier();
        newCourier.setDocument(request.document());
        newCourier.setName(request.name());
        newCourier.setEmail(request.email());
        newCourier.setPhone(request.phone());

        //SALVA NO DB
        Courier courierCreated = courierService.createCourier(newCourier);

        //TRANSFORMO A ENTITY SALVA NO DTO DE SAIDA
        var dto = new CourierResponseDTO(
                courierCreated.getId(),
                courierCreated.getName(),
                courierCreated.getPhone(),
                courierCreated.getStatus()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
