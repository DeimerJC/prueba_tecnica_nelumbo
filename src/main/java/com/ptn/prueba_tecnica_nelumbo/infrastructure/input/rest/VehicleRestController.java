package com.ptn.prueba_tecnica_nelumbo.infrastructure.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.VehicleRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.MessageResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.VehicleResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.handler.IVehicleHandler;

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
public class VehicleRestController {

    private final IVehicleHandler iVehicleHandler;

    @Operation(summary = "register entry into parking lot.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "recorded income"),
            @ApiResponse(responseCode = "400", description = "Bad Request", 
            		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            @ApiResponse(responseCode = "404", description = "No data found",
            		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))
    })
    @PostMapping("/vehicle/parking-income")
    public ResponseEntity<VehicleResponseDto> registerIncome(@Valid @RequestBody VehicleRequestDto vehicleRequestDto) {
        return new ResponseEntity<VehicleResponseDto>(iVehicleHandler.registerIncome(vehicleRequestDto), HttpStatus.CREATED);
    }


    @Operation(summary = "check out of the parking lot.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "registered output", 
            		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Message"))),
            @ApiResponse(responseCode = "400", description = "Bad Request", 
            		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
            @ApiResponse(responseCode = "404", description = "No data found",
            		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))
    })
    @PostMapping("/vehicle/parking-exit")
    public ResponseEntity<MessageResponseDto> checkOut(@Valid @RequestBody VehicleRequestDto vehicleRequestDto) {
        return new ResponseEntity<MessageResponseDto>(iVehicleHandler.checkOut(vehicleRequestDto), HttpStatus.OK);
    }
    
}
