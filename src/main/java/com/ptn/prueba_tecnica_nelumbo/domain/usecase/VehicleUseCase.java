package com.ptn.prueba_tecnica_nelumbo.domain.usecase;

import java.util.Date;
import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.domain.api.IVehicleServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.exception.BadRequestException;
import com.ptn.prueba_tecnica_nelumbo.domain.exception.NoDataFoundException;
import com.ptn.prueba_tecnica_nelumbo.domain.model.VehicleModel;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IVehiclePersistencePort;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration.Constants;

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
		VehicleModel vehicleModel;
		vehicleModel = iVehiclePersistencePort.getVehicle(vehicleId);
		
		if(vehicleModel == null) {
			throw new NoDataFoundException("No se encontró un usuario con el id "+vehicleId);
		}
		
		return vehicleModel;
	}

	@Override
	public VehicleModel updateVehicle(VehicleModel vehicleModel) {
		return iVehiclePersistencePort.updateVehicle(vehicleModel); 
	}

	@Override
	public void deleteVehicle(Long vehicleId) {
		iVehiclePersistencePort.deleteVehicle(vehicleId);
	}

	@Override
	public VehicleModel registerIncome(VehicleModel vehicleModel) {
		
		VehicleModel vehicleModeldb;
		vehicleModeldb = iVehiclePersistencePort.getByPlate(vehicleModel.getPlate());
		
		if(vehicleModeldb != null) {
			if(vehicleModeldb.getStatus().equals(Constants.STATUS_ENABLE)) {
				throw new BadRequestException("No se puede Registrar Ingreso, ya existe la placa en este u otro parqueadero");
			}
		}
		
		vehicleModel.setDateAdmission(new Date());
		vehicleModel.setCreation(new Date());
		vehicleModel.setStatus(Constants.STATUS_ENABLE);
		
		if(vehicleModeldb != null) {
			vehicleModel.setId(vehicleModeldb.getId());
			vehicleModel.setCreation(vehicleModeldb.getCreation());
		}
		
		return iVehiclePersistencePort.registerIncome(vehicleModel);
	}

}