package com.ptn.prueba_tecnica_nelumbo.domain.usecase;

import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.domain.api.IParkingHistoryServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.model.ParkingHistoryModel;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IParkingHistoryPersistencePort;

public class ParkingHistoryUseCase implements IParkingHistoryServicePort {

    private final IParkingHistoryPersistencePort iParkingHistoryPersistencePort;

    public ParkingHistoryUseCase(IParkingHistoryPersistencePort iParkingHistoryPersistencePort) {
        this.iParkingHistoryPersistencePort = iParkingHistoryPersistencePort;
    }

	@Override
	public ParkingHistoryModel saveParkingHistory(ParkingHistoryModel parkingHistoryModel) {
		return iParkingHistoryPersistencePort.saveParkingHistory(parkingHistoryModel);
	}

	@Override
	public List<ParkingHistoryModel> getAllParkingHistorys() {
		return iParkingHistoryPersistencePort.getAllParkingHistorys();
	}

	@Override
	public ParkingHistoryModel getParkingHistory(Long parkingHistoryId) {
		return iParkingHistoryPersistencePort.getParkingHistory(parkingHistoryId);
	}

	@Override
	public ParkingHistoryModel updateParkingHistory(ParkingHistoryModel parkingHistoryModel) {
		return iParkingHistoryPersistencePort.updateParkingHistory(parkingHistoryModel); 
	}

	@Override
	public void deleteParkingHistory(Long parkingHistoryId) {
		iParkingHistoryPersistencePort.deleteParkingHistory(parkingHistoryId);
	}

}