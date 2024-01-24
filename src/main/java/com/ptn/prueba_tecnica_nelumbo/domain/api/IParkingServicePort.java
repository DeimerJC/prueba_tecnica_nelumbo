package com.ptn.prueba_tecnica_nelumbo.domain.api;

import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.application.dto.response.CreatedObjectResponseDto;
import com.ptn.prueba_tecnica_nelumbo.domain.model.ParkingModel;

public interface IParkingServicePort {
	
	CreatedObjectResponseDto saveParking(ParkingModel parkingModel);

    List<ParkingModel> getAllParkings();

    ParkingModel getParking(Long parkingId);

    ParkingModel updateParking(ParkingModel parkingModel);

    void deleteParking(Long parkingId);
    
}