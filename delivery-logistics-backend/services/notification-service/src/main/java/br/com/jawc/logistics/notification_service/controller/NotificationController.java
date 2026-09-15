/**
 * @author jawc
 */
package br.com.jawc.logistics.notification_service.controller;

import br.com.jawc.logistics.notification_service.domain.Notification;
import br.com.jawc.logistics.notification_service.dto.NotificationRequestDTO;
import br.com.jawc.logistics.notification_service.dto.NotificationResponseDTO;
import br.com.jawc.logistics.notification_service.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    @Operation(summary = "Create a Notification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The Notification was created!"),
            @ApiResponse(responseCode = "400", description = "Validation error or duplicate key")
    })
    public ResponseEntity<NotificationResponseDTO> createNotication(@RequestBody @Valid NotificationRequestDTO request){
        //Transforma a entrada em entity q vai ao banco
        Notification newNotification = new Notification();
        newNotification.setOrderId(request.orderId());
        newNotification.setMessage(request.message());

        //SALVA NO DB
        Notification notificationCreated = notificationService.createNotification(newNotification);

        //TRANSFORMA A ENTITYU SALVA NO DTO DE SAIDA
        var dto = new NotificationResponseDTO(
                notificationCreated.getId(),
                notificationCreated.getOrderId(),
                notificationCreated.getMessage(),
                notificationCreated.getCreatedAt()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }


}
