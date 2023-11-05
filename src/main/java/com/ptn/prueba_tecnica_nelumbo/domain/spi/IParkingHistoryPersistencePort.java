package com.ptn.prueba_tecnica_nelumbo.domain.spi;

import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.domain.model.ParkingHistoryModel;

public interface IParkingHistoryPersistencePort {
	
	ParkingHistoryModel saveParkingHistory(ParkingHistoryModel parkingHistoryModel);

    List<ParkingHistoryModel> getAllParkingHistorys();

    ParkingHistoryModel getParkingHistory(Long parkingHistoryId);

    ParkingHistoryModel updateParkingHistory(ParkingHistoryModel parkingHistoryModel);

    void deleteParkingHistory(Long parkingHistoryId);

	Double parkingProfitsDay(Long parkingId);

	Double parkingProfitsWeek(Long parkingId);

	Double parkingProfitsMonth(Long parkingId);

	Double parkingProfitsYear(Long parkingId);

	List<Object[]> mostRegisteredVehicles();

	List<Object[]> mostRegisteredVehiclesByParking(Long parkingId);

}