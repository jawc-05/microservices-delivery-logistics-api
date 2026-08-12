/**
 * @author jawc
 */
package br.com.jawc.logistics.auth_service.controller;

import br.com.jawc.logistics.auth_service.domain.User;
import br.com.jawc.logistics.auth_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
