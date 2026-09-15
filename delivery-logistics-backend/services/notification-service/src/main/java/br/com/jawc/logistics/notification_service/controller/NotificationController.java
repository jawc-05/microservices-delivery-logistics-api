/**
 * @author jawc
 */
package br.com.jawc.logistics.notification_service.controller;

import br.com.jawc.logistics.notification_service.domain.Notification;
import br.com.jawc.logistics.notification_service.dto.NotificationRequestDTO;
import br.com.jawc.logistics.notification_service.dto.NotificationResponseDTO;
import br.com.jawc.logistics.notification_service.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/order/{orderId}")
    @Operation(summary = "Search the notification by order id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returns the notification by id"),
            @ApiResponse(responseCode = "404", description = "This ORDER doesnt have an notification"),
            @ApiResponse(responseCode = "400", description = "syntax error or bad request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "BAD_REQUEST"))),
            @ApiResponse(responseCode = "500", description = "An exception was made",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "INTERNAL_SERVER_ERROR"))),
    })
    public ResponseEntity<Page<NotificationResponseDTO>> searchNotifications(
            @PathVariable Long orderId,
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {

        //ACHANDO NOTIFICATION
        Page<Notification> notifications = notificationService.getNotificationsByOrderId(orderId, pageable);

        //CRIANDO DTO
        Page<NotificationResponseDTO> responseDTO = notifications.map(notification -> new NotificationResponseDTO(
                notification.getId(),
                notification.getOrderId(),
                notification.getMessage(),
                notification.getCreatedAt()
        ));
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

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
