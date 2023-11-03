package com.ptn.prueba_tecnica_nelumbo.infrastructure.input.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.UserRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.UserResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.handler.IUserHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserRestController {

    private final IUserHandler iUserHandler;

    @Operation(summary = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", 
            		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            @ApiResponse(responseCode = "409", description = "User already exists",
            		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))
    })
    @PostMapping("/user")
    public ResponseEntity<UserResponseDto> saveUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<UserResponseDto>(iUserHandler.saveUser(userRequestDto), HttpStatus.CREATED);
    }
    

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All users returned"),
            @ApiResponse(responseCode = "400", description = "Bad Request", 
            		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            @ApiResponse(responseCode = "404", description = "No data found", 
    		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
    })
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(iUserHandler.getAllUsers());
    }
    
    
    @Operation(summary = "Get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User returned"),
            @ApiResponse(responseCode = "400", description = "Bad Request", 
            		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            @ApiResponse(responseCode = "404", description = "No data found", 
    		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
    })
    @GetMapping("/user/{idUser}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long idUser) {
        return ResponseEntity.ok(iUserHandler.getUser(idUser));
    }
    
    
    @Operation(summary = "Update user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request", 
            		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            @ApiResponse(responseCode = "404", description = "No data found", 
    		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
    })
	@PutMapping("/user")
	public ResponseEntity<UserResponseDto> updateUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<UserResponseDto>(iUserHandler.updateUser(userRequestDto), HttpStatus.OK);
	}
    
    
    @Operation(summary = "Delete user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted"),
            @ApiResponse(responseCode = "400", description = "Bad Request", 
            		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            @ApiResponse(responseCode = "404", description = "No data found", 
    		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
    })
	@DeleteMapping("/user/{idUser}")
	public ResponseEntity<?> deleteUser(@PathVariable Long idUser) {
    	iUserHandler.deleteUser(idUser);
        return new ResponseEntity<>(HttpStatus.OK);
	}
    
    
    @Operation(summary = "Get user by username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User returned"),
            @ApiResponse(responseCode = "400", description = "Bad Request", 
            		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            @ApiResponse(responseCode = "404", description = "No data found", 
    		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
    })
    @GetMapping("/user/username/{username}")
    public ResponseEntity<UserResponseDto> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(iUserHandler.getByUsername(username));
    }

}