package com.ptn.prueba_tecnica_nelumbo.domain.spi;

import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.domain.model.ParkingModel;

public interface IParkingPersistencePort {
	
	ParkingModel saveParking(ParkingModel parkingModel);

    List<ParkingModel> getAllParkings();

    ParkingModel getParking(Long parkingId);

    ParkingModel updateParking(ParkingModel parkingModel);

    void deleteParking(Long parkingId);
    
    ParkingModel getParkingByName(String name);
    
}