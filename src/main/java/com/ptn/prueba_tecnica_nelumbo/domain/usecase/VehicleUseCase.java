package com.ptn.prueba_tecnica_nelumbo.domain.usecase;

import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.domain.api.IVehicleServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.model.VehicleModel;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IVehiclePersistencePort;

public class VehicleUseCase implements IVehicleServicePort {

    private final IVehiclePersistencePort iVehiclePersistencePort;

    public VehicleUseCase(IVehiclePersistencePort iVehiclePersistencePort) {
        this.iVehiclePersistencePort = iVehiclePersistencePort;
    }

	@Override
	public VehicleModel saveVehicle(VehicleModel vehicleModel) {
		return iVehiclePersistencePort.saveVehicle(vehicleModel);
	}

	@Override
	public List<VehicleModel> getAllVehicles() {
		return iVehiclePersistencePort.getAllVehicles();
	}

	@Override
	public VehicleModel getVehicle(Long vehicleId) {
		return iVehiclePersistencePort.getVehicle(vehicleId);
	}

	@Override
	public VehicleModel updateVehicle(VehicleModel vehicleModel) {
		return iVehiclePersistencePort.updateVehicle(vehicleModel); 
	}

	@Override
	public void deleteVehicle(Long vehicleId) {
		iVehiclePersistencePort.deleteVehicle(vehicleId);
	}

}