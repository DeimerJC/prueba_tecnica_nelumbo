package com.ptn.prueba_tecnica_nelumbo.domain.usecase;

import java.util.Date;
import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.domain.api.IParkingServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.exception.NoDataFoundException;
import com.ptn.prueba_tecnica_nelumbo.domain.model.ParkingModel;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IParkingPersistencePort;

public class ParkingUseCase implements IParkingServicePort {

    private final IParkingPersistencePort iParkingPersistencePort;

    public ParkingUseCase(IParkingPersistencePort iParkingPersistencePort) {
        this.iParkingPersistencePort = iParkingPersistencePort;
    }

	@Override
	public ParkingModel saveParking(ParkingModel parkingModel) {
		parkingModel.setCreation(new Date()); 
		return iParkingPersistencePort.saveParking(parkingModel);
	}

	@Override
	public List<ParkingModel> getAllParkings() {
		return iParkingPersistencePort.getAllParkings();
	}

	@Override
	public ParkingModel getParking(Long parkingId) {
		ParkingModel parkingModel;
		parkingModel = iParkingPersistencePort.getParking(parkingId);
		
		if(parkingModel == null) {
			throw new NoDataFoundException("No se encontró un parqueadero con el id "+parkingId);
		}
		
		return parkingModel;
	}

	@Override
	public ParkingModel updateParking(ParkingModel parkingModel) {
		
		getParking(parkingModel.getId());
		
		return iParkingPersistencePort.updateParking(parkingModel); 
	}

	@Override
	public void deleteParking(Long parkingId) {
		
		getParking(parkingId);
		
		iParkingPersistencePort.deleteParking(parkingId);
	}

}