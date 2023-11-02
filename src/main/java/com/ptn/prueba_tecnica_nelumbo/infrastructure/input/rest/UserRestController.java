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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.UserRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.UserResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.handler.IUserHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserRestController {

    private final IUserHandler iUserHandler;

    @Operation(summary = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    @PostMapping("/")
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserRequestDto userRequestDto) {
    	iUserHandler.saveUser(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All users returned", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(iUserHandler.getAllUsers());
    }
    
    
    @Operation(summary = "Get users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User returned", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/{idUser}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long idUser) {
        return ResponseEntity.ok(iUserHandler.getUser(idUser));
    }
    
    
    @Operation(summary = "Update users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
	@PutMapping("/")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserRequestDto userRequestDto) {
    	iUserHandler.updateUser(userRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
	}
    
    
    @Operation(summary = "Delete users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
	@DeleteMapping("/{idUser}")
	public ResponseEntity<?> deleteUser(@PathVariable Long idUser) {
    	iUserHandler.deleteUser(idUser);
        return new ResponseEntity<>(HttpStatus.OK);
	}
    
    
    @Operation(summary = "Get users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User returned", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/findByUsername")
    public ResponseEntity<UserResponseDto> getByUsername(@RequestParam String username) {
        return ResponseEntity.ok(iUserHandler.getByUsername(username));
    }

}