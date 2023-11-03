package com.ptn.prueba_tecnica_nelumbo.application.handler;

import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.VehicleRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.VehicleResponseDto;

public interface IVehicleHandler {
	
	VehicleResponseDto saveVehicle(VehicleRequestDto vehicleRequestDto);

    List<VehicleResponseDto> getAllVehicles();

    VehicleResponseDto getVehicle(Long vehicleId);

    VehicleResponseDto updateVehicle(VehicleRequestDto vehicleRequestDto);

    void deleteVehicle(Long vehicleId);

}