package com.ptn.prueba_tecnica_nelumbo.infrastructure.input.rest;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.VehicleExitRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.request.VehicleRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.MessageResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.VehicleResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.VehicleResponseExtDto;
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
    @PostMapping("/parkings/{parkingId}/vehicle")
    public ResponseEntity<VehicleResponseDto> registerIncome(@PathVariable Long parkingId,  @Valid @RequestBody VehicleRequestDto vehicleRequestDto, @RequestHeader HttpHeaders headers) {
        return new ResponseEntity<VehicleResponseDto>(iVehicleHandler.registerIncome(parkingId, vehicleRequestDto, headers.get("Authorization").get(0).substring(7)), HttpStatus.CREATED);
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
    @DeleteMapping("/vehicle/parking-exit")
    public ResponseEntity<MessageResponseDto> checkOut(@Valid @RequestBody VehicleExitRequestDto vehicleExitRequestDto) {
        return new ResponseEntity<MessageResponseDto>(iVehicleHandler.checkOut(vehicleExitRequestDto), HttpStatus.OK);
    }
    

    @Operation(summary = "Obtain all vehicles parked per parking lot. The vehicles are shown if the parking lot belongs to the user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All vehicles returned"),
            @ApiResponse(responseCode = "404", description = "No data found", 
    		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
    })
    @GetMapping("/vehicles/{idParking}")
    public ResponseEntity<List<VehicleResponseExtDto>> getAllVehicles(@PathVariable Long idParking, @RequestHeader HttpHeaders headers) {
        return ResponseEntity.ok(iVehicleHandler.getAllVehicles(idParking, headers.get("Authorization").get(0).substring(7)));
    }
    
}
