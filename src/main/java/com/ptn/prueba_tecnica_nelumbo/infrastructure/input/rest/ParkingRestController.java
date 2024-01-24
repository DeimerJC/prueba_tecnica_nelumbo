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

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.ParkingRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.CreatedObjectResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.ParkingResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.handler.IParkingHandler;

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
public class ParkingRestController {

    private final IParkingHandler iParkingHandler;

    @Operation(summary = "Add a new Parking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Parking created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", 
            		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            @ApiResponse(responseCode = "409", description = "Parking already exists",
            		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))
    })
    @PostMapping("/parking")
    public ResponseEntity<CreatedObjectResponseDto> saveParking(@Valid @RequestBody ParkingRequestDto parkingRequestDto) {
        return new ResponseEntity<CreatedObjectResponseDto>(iParkingHandler.saveParking(parkingRequestDto), HttpStatus.CREATED);
    }
    

    @Operation(summary = "Get all Parkings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Parkings returned"),
            @ApiResponse(responseCode = "400", description = "Bad Request", 
            		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            @ApiResponse(responseCode = "404", description = "No data found", 
    		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
    })
    @GetMapping("/parkings")
    public ResponseEntity<List<ParkingResponseDto>> getAllParkings() {
        return ResponseEntity.ok(iParkingHandler.getAllParkings());
    }
    
    
    @Operation(summary = "Get Parking by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parking returned"),
            @ApiResponse(responseCode = "400", description = "Bad Request", 
            		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            @ApiResponse(responseCode = "404", description = "No data found", 
    		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
    })
    @GetMapping("/parking/{idParking}")
    public ResponseEntity<ParkingResponseDto> getParking(@PathVariable Long idParking) {
        return ResponseEntity.ok(iParkingHandler.getParking(idParking));
    }
    
    
    @Operation(summary = "Update Parking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parking updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request", 
            		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            @ApiResponse(responseCode = "404", description = "No data found", 
    		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
    })
	@PutMapping("/parking")
	public ResponseEntity<ParkingResponseDto> updateParking(@Valid @RequestBody ParkingRequestDto parkingRequestDto) {
        return new ResponseEntity<ParkingResponseDto>(iParkingHandler.updateParking(parkingRequestDto), HttpStatus.OK);
	}
    
    
    @Operation(summary = "Delete Parking by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parking deleted"),
            @ApiResponse(responseCode = "400", description = "Bad Request", 
            		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            @ApiResponse(responseCode = "404", description = "No data found", 
    		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
    })
	@DeleteMapping("/parking/{idParking}")
	public ResponseEntity<?> deleteParking(@PathVariable Long idParking) {
    	iParkingHandler.deleteParking(idParking);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}