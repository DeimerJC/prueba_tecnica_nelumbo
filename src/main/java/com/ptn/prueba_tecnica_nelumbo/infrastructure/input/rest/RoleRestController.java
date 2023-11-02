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

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.RoleRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.RoleResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.handler.IRoleHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class RoleRestController {

    private final IRoleHandler iRoleHandler;

    @Operation(summary = "Add a new Role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Role created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "409", description = "Role already exists")
    })
    @PostMapping("/")
    public ResponseEntity<?> saveRole(@Valid @RequestBody RoleRequestDto RoleRequestDto) {
    	iRoleHandler.saveRole(RoleRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    

    @Operation(summary = "Get all Roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Roles returned", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<RoleResponseDto>> getAllRoles() {
        return ResponseEntity.ok(iRoleHandler.getAllRoles());
    }
    
    
    @Operation(summary = "Get Roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role returned", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/{idRole}")
    public ResponseEntity<RoleResponseDto> getRole(@PathVariable Long idRole) {
        return ResponseEntity.ok(iRoleHandler.getRole(idRole));
    }
    
    
    @Operation(summary = "Get Role by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role returned", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/findByRoleName/{name}")
    public ResponseEntity<RoleResponseDto> getRoleByName(@PathVariable String name) {
        return ResponseEntity.ok(iRoleHandler.getRoleByName(name));
    }
    
    
    @Operation(summary = "Update Roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
	@PutMapping("/")
	public ResponseEntity<?> updateRole(@Valid @RequestBody RoleRequestDto RoleRequestDto) {
    	iRoleHandler.updateRole(RoleRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
	}
    
    
    @Operation(summary = "Delete Roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role deleted", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
	@DeleteMapping("/{idRole}")
	public ResponseEntity<?> deleteRole(@PathVariable Long idRole) {
    	iRoleHandler.deleteRole(idRole);
        return new ResponseEntity<>(HttpStatus.OK);
	}

}