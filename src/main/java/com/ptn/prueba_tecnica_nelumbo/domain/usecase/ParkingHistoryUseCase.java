package com.ptn.prueba_tecnica_nelumbo.domain.usecase;

import java.util.Date;
import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.domain.api.IParkingHistoryServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.api.IParkingServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.exception.NoDataFoundException;
import com.ptn.prueba_tecnica_nelumbo.domain.model.ParkingHistoryModel;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IParkingHistoryPersistencePort;

public class ParkingHistoryUseCase implements IParkingHistoryServicePort {

    private final IParkingHistoryPersistencePort iParkingHistoryPersistencePort;
    
    private final IParkingServicePort iParkingServicePort;

    public ParkingHistoryUseCase(IParkingHistoryPersistencePort iParkingHistoryPersistencePort,
    		IParkingServicePort iParkingServicePort) {
        this.iParkingHistoryPersistencePort = iParkingHistoryPersistencePort;
        this.iParkingServicePort = iParkingServicePort;
    }

	@Override
	public ParkingHistoryModel saveParkingHistory(ParkingHistoryModel parkingHistoryModel) {
		parkingHistoryModel.setCreation(new Date()); 
		return iParkingHistoryPersistencePort.saveParkingHistory(parkingHistoryModel);
	}

	@Override
	public List<ParkingHistoryModel> getAllParkingHistorys() {
		return iParkingHistoryPersistencePort.getAllParkingHistorys();
	}

	@Override
	public ParkingHistoryModel getParkingHistory(Long parkingHistoryId) {

		ParkingHistoryModel parkingHistoryModel;
		parkingHistoryModel = iParkingHistoryPersistencePort.getParkingHistory(parkingHistoryId);
		
		if(parkingHistoryModel == null) {
			throw new NoDataFoundException("No se encontró un historial con el id "+parkingHistoryId);
		}
		return parkingHistoryModel;
	}

	@Override
	public ParkingHistoryModel updateParkingHistory(ParkingHistoryModel parkingHistoryModel) {
		return iParkingHistoryPersistencePort.updateParkingHistory(parkingHistoryModel); 
	}

	@Override
	public void deleteParkingHistory(Long parkingHistoryId) {
		iParkingHistoryPersistencePort.deleteParkingHistory(parkingHistoryId);
	}

	@Override
	public Double parkingProfitsDay(Long parkingId) {
		return iParkingHistoryPersistencePort.parkingProfitsDay(parkingId);
	}

	@Override
	public Double parkingProfitsWeek(Long parkingId) {
		Double pofrits = iParkingHistoryPersistencePort.parkingProfitsWeek(parkingId);
		return (pofrits != null ? pofrits : 0);
	}

	@Override
	public Double parkingProfitsMonth(Long parkingId) {
		return iParkingHistoryPersistencePort.parkingProfitsMonth(parkingId);
	}

	@Override
	public Double parkingProfitsYear(Long parkingId) {
		parkingExists(parkingId);
		Double pofrits = iParkingHistoryPersistencePort.parkingProfitsWeek(parkingId);
		return (pofrits != null ? pofrits : 0);
	}
	

	@Override
	public List<Object[]> mostRegisteredVehicles() {
		List<Object[]> list = iParkingHistoryPersistencePort.mostRegisteredVehicles();
		
		if(list.isEmpty()) {
			throw new NoDataFoundException("No se encontró vehiculos");
		}
		
		return list;
	}

	@Override
	public List<Object[]> mostRegisteredVehiclesByParking(Long parkingId) {
		List<Object[]> list = iParkingHistoryPersistencePort.mostRegisteredVehiclesByParking(parkingId);
		
		if(list.isEmpty()) {
			throw new NoDataFoundException("No se encontró vehiculos");
		}
		
		return list;
	}

	private void parkingExists(Long parkingId) {
		iParkingServicePort.getParking(parkingId);
	}

}