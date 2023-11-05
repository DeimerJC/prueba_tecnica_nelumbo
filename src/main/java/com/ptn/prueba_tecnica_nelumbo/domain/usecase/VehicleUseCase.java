package com.ptn.prueba_tecnica_nelumbo.domain.usecase;

import java.util.Date;
import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.application.dto.response.MessageResponseDto;
import com.ptn.prueba_tecnica_nelumbo.domain.api.IParkingHistoryServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.api.IParkingServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.api.IVehicleServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.exception.BadRequestException;
import com.ptn.prueba_tecnica_nelumbo.domain.exception.NoDataFoundException;
import com.ptn.prueba_tecnica_nelumbo.domain.model.ParkingHistoryModel;
import com.ptn.prueba_tecnica_nelumbo.domain.model.VehicleModel;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IVehiclePersistencePort;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration.Constants;

public class VehicleUseCase implements IVehicleServicePort {

    private final IVehiclePersistencePort iVehiclePersistencePort;
    private final IParkingServicePort iParkingServicePort;
    private final IParkingHistoryServicePort iParkingHistoryServicePort;

    public VehicleUseCase(IVehiclePersistencePort iVehiclePersistencePort,
    		IParkingServicePort iParkingServicePort,
    		IParkingHistoryServicePort iParkingHistoryServicePort) {
        this.iVehiclePersistencePort = iVehiclePersistencePort;
        this.iParkingServicePort = iParkingServicePort;
        this.iParkingHistoryServicePort = iParkingHistoryServicePort;
    }

	@Override
	public VehicleModel saveVehicle(VehicleModel vehicleModel) {
		return iVehiclePersistencePort.saveVehicle(vehicleModel);
	}

	@Override
	public List<VehicleModel> getAllVehicles() {
		List<VehicleModel> vehicleModelList = iVehiclePersistencePort.getAllVehicles();
		
		if(vehicleModelList.isEmpty()) {
			throw new NoDataFoundException("No se encontró vehiculos");
		}
		
		return vehicleModelList;
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
		
		iParkingServicePort.getParking(vehicleModel.getParkingModel().getId());			
		
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

	@Override
	public MessageResponseDto checkOut(VehicleModel vehicleModel) {
		
		iParkingServicePort.getParking(vehicleModel.getParkingModel().getId());			
		
		VehicleModel vehicleModeldb;
		vehicleModeldb = iVehiclePersistencePort.getByPlate(vehicleModel.getPlate());
		
		if (vehicleModeldb != null) {
			if (vehicleModeldb.getStatus().equals(Constants.STATUS_DISABLE)) {
				throw new BadRequestException("No se puede Registrar Salida, no existe la placa en el parqueadero");
			} else if (!vehicleModeldb.getParkingModel().getId().equals(vehicleModel.getParkingModel().getId())) {
				throw new BadRequestException("No se puede Registrar Salida, no existe la placa en el parqueadero");
			}
		} else {
			throw new BadRequestException("No se puede Registrar Salida, no existe la placa en el parqueadero");
		}
		
		vehicleModeldb.setStatus(Constants.STATUS_DISABLE);
		
		updateVehicle(vehicleModeldb);
		
		//Se registra en la tabla historial de parqueaderos
		ParkingHistoryModel parkingHistoryModel;
		parkingHistoryModel = new ParkingHistoryModel(null, vehicleModeldb, vehicleModeldb.getParkingModel(), vehicleModeldb.getDateAdmission(), new Date(), null);
		
		iParkingHistoryServicePort.saveParkingHistory(parkingHistoryModel);
		
		return new MessageResponseDto("Salida registrada");
	}

	@Override
	public List<VehicleModel> vehiclesByCoincidence(String plateSearch) {
		List<VehicleModel> vehicleModelList = iVehiclePersistencePort.vehiclesByCoincidence(plateSearch);
		
		if(vehicleModelList.isEmpty()) {
			throw new NoDataFoundException("No se encontró vehiculos");
		}
		
		return vehicleModelList;
	}

	@Override
	public List<VehicleModel> vehiclesParkedFirstTime() {
		List<VehicleModel> vehicleModelList = iVehiclePersistencePort.vehiclesParkedFirstTime();
		
		if(vehicleModelList.isEmpty()) {
			throw new NoDataFoundException("No se encontró vehiculos");
		}
		
		return vehicleModelList;
	}

}