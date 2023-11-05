package com.ptn.prueba_tecnica_nelumbo.infrastructure.input.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptn.prueba_tecnica_nelumbo.application.dto.response.VehicleResponseExtDto;
import com.ptn.prueba_tecnica_nelumbo.application.handler.IParkingHistoryHandler;
import com.ptn.prueba_tecnica_nelumbo.application.handler.IVehicleHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/indicators")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class IndicatorsRestController {

    private final IVehicleHandler iVehicleHandler;
    private final IParkingHistoryHandler iParkingHistoryHandler;

    
    @Operation(summary = "Search parked vehicles by matching the license plate.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All vehicles returned"),
            @ApiResponse(responseCode = "404", description = "No data found", 
    		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
    })
    @GetMapping("/vehicles-coincidence/{plateSearch}")
    public ResponseEntity<List<VehicleResponseExtDto>> vehiclesByCoincidence(@PathVariable String plateSearch) {
        return ResponseEntity.ok(iVehicleHandler.vehiclesByCoincidence(plateSearch));
    }
    

    @Operation(summary = "Vehicles parked for the first time in a parking lot.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All vehicles returned"),
            @ApiResponse(responseCode = "404", description = "No data found", 
    		content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
    })
    @GetMapping("/vehicles-first-time")
    public ResponseEntity<List<VehicleResponseExtDto>> vehiclesByCoincidence() {
        return ResponseEntity.ok(iVehicleHandler.vehiclesParkedFirstTime());
    }
    
}
