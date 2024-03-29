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
import com.ptn.prueba_tecnica_nelumbo.domain.model.ParkingModel;
import com.ptn.prueba_tecnica_nelumbo.domain.model.VehicleModel;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IVehiclePersistencePort;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration.Constants;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration.jwt.JwtUtilService;

public class VehicleUseCase implements IVehicleServicePort {

    private final IVehiclePersistencePort iVehiclePersistencePort;
    private final IParkingServicePort iParkingServicePort;
    private final IParkingHistoryServicePort iParkingHistoryServicePort;
    private final JwtUtilService jwtUtilService;

    public VehicleUseCase(IVehiclePersistencePort iVehiclePersistencePort,
    		IParkingServicePort iParkingServicePort,
    		IParkingHistoryServicePort iParkingHistoryServicePort,
    		JwtUtilService jwtUtilService) {
        this.iVehiclePersistencePort = iVehiclePersistencePort;
        this.iParkingServicePort = iParkingServicePort;
        this.iParkingHistoryServicePort = iParkingHistoryServicePort;
        this.jwtUtilService = jwtUtilService;
    }

	@Override
	public VehicleModel saveVehicle(VehicleModel vehicleModel) {
		return iVehiclePersistencePort.saveVehicle(vehicleModel);
	}

	@Override
	public List<VehicleModel> getAllVehicles(Long idParking, String token) {
		List<VehicleModel> vehicleModelList = null;
		
		String role = jwtUtilService.getRolFromToken(token);
		Long idUser = Long.valueOf(jwtUtilService.getIdUsuarioFromToken(token));
		
		if (role.equals(Constants.ROLE_SOCIO)) {
			ParkingModel parkingModel = iParkingServicePort.getParking(idParking);	
			if (!parkingModel.getUserModel().getId().equals(idUser)) {
				throw new BadRequestException("El parqueadero no pertenece al usuario");
			}
		}
		
		vehicleModelList = iVehiclePersistencePort.getAllVehiclesByParking(idParking);
		
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
	public VehicleModel registerIncome(VehicleModel vehicleModel, String token) {
		
		String role = jwtUtilService.getRolFromToken(token);
		Long idUser = Long.valueOf(jwtUtilService.getIdUsuarioFromToken(token));
		
		if (role.equals(Constants.ROLE_SOCIO)) {
			ParkingModel parkingModel = iParkingServicePort.getParking(vehicleModel.getParkingModel().getId());	
			if (!parkingModel.getUserModel().getId().equals(idUser)) {
				throw new BadRequestException("El parqueadero no pertenece al usuario");
			}
		} else {
			throw new BadRequestException("El usuario no tiene permisos.");
		}
		
		iParkingServicePort.getParking(vehicleModel.getParkingModel().getId());	
		
		if (iVehiclePersistencePort.verifyLimitVehicles(vehicleModel.getParkingModel().getId())) {
			throw new BadRequestException("No se puede Registrar Ingreso, el parqueadero se encuentra al limite de vehiculos");
		}
		
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