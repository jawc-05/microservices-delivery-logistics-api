/**
 * @author jawc
 */
package br.com.jawc.logistics.auth_service.controller;

import br.com.jawc.logistics.auth_service.domain.User;
import br.com.jawc.logistics.auth_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "Endpoints for User management")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Get all Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the list of Users"),
            @ApiResponse(responseCode = "400", description = "Bad syntax or bad request"),
    })
    public ResponseEntity<Page<User>> searchUsers(Pageable pageable){
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @GetMapping(value = "/email/{email}")
    @Operation(summary = "Find the user with the email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the user founded")
            @ApiResponse(responseCode = "400", description = "Bad syntax or bad request"),
    })
    public ResponseEntity<User> searchByEmail(@PathVariable(value = "email", required = true)String email){
        return ResponseEntity.ok(userService.findByEmail(email));
    }


    @PostMapping
    @Operation(summary = "Create a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create the user"),
            @ApiResponse(responseCode = "400", description = "Validation error or duplicate key"),
    })
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
    }



}
