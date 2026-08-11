/**
 * @author jawc
 */
package br.com.jawc.logistics.order_service.controller;

import br.com.jawc.logistics.order_service.domain.Order;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @ApiResponse(responseCode = "400", description = "sintax error or bad request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "BAD_REQUEST"))),
            @ApiResponse(responseCode = "500", description = "An exception was made",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "INTERNAL_SERVER_ERROR"))),
    })
    public ResponseEntity<Page<Order>> searchOrders(Pageable pageable){
        return ResponseEntity.ok(orderService.getAllOrders(pageable));
    }

    @PostMapping
    @Operation(summary = "Create a order")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Create a order"),
            @ApiResponse(responseCode = "400", description = "Validation error or duplicate key")
    })
    public ResponseEntity<Order> createOrder(@RequestBody @Valid Order order){
        return ResponseEntity.ok(orderService.createOrder(order));

    }
}
