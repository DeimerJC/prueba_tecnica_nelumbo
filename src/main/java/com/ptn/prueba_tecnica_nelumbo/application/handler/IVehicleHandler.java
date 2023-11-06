package com.ptn.prueba_tecnica_nelumbo.application.handler;

import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.VehicleRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.MessageResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.VehicleResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.VehicleResponseExtDto;

public interface IVehicleHandler {
	
	VehicleResponseDto saveVehicle(VehicleRequestDto vehicleRequestDto);

    List<VehicleResponseExtDto> getAllVehicles(Long idParking, String token);

    VehicleResponseDto getVehicle(Long vehicleId);

    VehicleResponseDto updateVehicle(VehicleRequestDto vehicleRequestDto);

    void deleteVehicle(Long vehicleId);

	VehicleResponseDto registerIncome(VehicleRequestDto vehicleRequestDto);

	MessageResponseDto checkOut(VehicleRequestDto vehicleRequestDto);

	List<VehicleResponseExtDto> vehiclesByCoincidence(String plateSearch);

	List<VehicleResponseExtDto> vehiclesParkedFirstTime();  

}