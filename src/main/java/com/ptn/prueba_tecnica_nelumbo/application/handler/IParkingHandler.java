package com.ptn.prueba_tecnica_nelumbo.application.handler;

import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.ParkingRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.CreatedObjectResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.ParkingResponseDto;

public interface IParkingHandler {
	
	CreatedObjectResponseDto saveParking(ParkingRequestDto parkingRequestDto);

    List<ParkingResponseDto> getAllParkings();

    ParkingResponseDto getParking(Long parkingId);

    ParkingResponseDto updateParking(ParkingRequestDto parkingRequestDto);

    void deleteParking(Long parkingId);

}