/**
 * @author jawc
 */
package br.com.jawc.logistics.order_service.controller;

import br.com.jawc.logistics.order_service.domain.Order;
import br.com.jawc.logistics.order_service.dto.OrderRequestDTO;
import br.com.jawc.logistics.order_service.dto.OrderResponseDTO;
import br.com.jawc.logistics.order_service.dto.OrdersPerDayDTO;
import br.com.jawc.logistics.order_service.service.OrderService;
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

import java.util.List;

@RestController
@RequestMapping(value = "/api/orders")
@RequiredArgsConstructor
@Tag(name = "Order", description = "Endpoints for Order management")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @Operation(summary = "Get all orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the list of orders"),
            @ApiResponse(responseCode = "400", description = "syntax error or bad request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "BAD_REQUEST"))),
            @ApiResponse(responseCode = "500", description = "An exception was made",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "INTERNAL_SERVER_ERROR"))),
    })
    public ResponseEntity<Page<OrderResponseDTO>> searchOrders(Pageable pageable){
        // Aqui acho as orders
        Page<Order> ordersPage = orderService.getAllOrders(pageable);

        //Aqui eu crio um dtoPage
        Page<OrderResponseDTO> dtoPage = ordersPage.map(order -> new OrderResponseDTO(
                order.getId(),
                order.getCustomerEmail(),
                order.getCustomerName(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getCreatedAt()
        ));


        return ResponseEntity.status(HttpStatus.OK).body(dtoPage);
    }

    @PostMapping
    @Operation(summary = "Create a order")
    @ApiResponses(value={
            @ApiResponse(responseCode = "201", description = "The order was created"),
            @ApiResponse(responseCode = "400", description = "Validation error or duplicate key")
    })
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody @Valid OrderRequestDTO request){

        //Transformar o DTO de entrada na Entidade que vai pro banco
        Order newOrder = new Order();
        newOrder.setCustomerName(request.customerName());
        newOrder.setCustomerEmail(request.customerEmail());
        newOrder.setTotalAmount(request.totalAmount());

        //SALVA NO BANCO
        Order orderCreated = orderService.createOrder(newOrder);

       // Transformar a Entidade salva no DTO de saída (usando o orderCreated!)
        var dto = new OrderResponseDTO(
                orderCreated.getId(),
                orderCreated.getCustomerEmail(),
                orderCreated.getCustomerName(),
                orderCreated.getTotalAmount(),
                orderCreated.getStatus(),
                orderCreated.getCreatedAt()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/reports/daily")
    @Operation(summary = "Get the reports per day")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return the list of orders per day"),
            @ApiResponse(responseCode = "400", description = "syntax error or bad request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "BAD_REQUEST"))),
            @ApiResponse(responseCode = "500", description = "An exception was made",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "INTERNAL_SERVER_ERROR")))
    })
    public ResponseEntity<List<OrdersPerDayDTO>> getOrdersPerDay(){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrdersPerDayReport());
    }
}
