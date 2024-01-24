package com.ptn.prueba_tecnica_nelumbo.domain.usecase;

import java.util.Date;
import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.application.dto.response.CreatedObjectResponseDto;
import com.ptn.prueba_tecnica_nelumbo.domain.api.IParkingServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.api.IUserServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.exception.BadRequestException;
import com.ptn.prueba_tecnica_nelumbo.domain.exception.NoDataFoundException;
import com.ptn.prueba_tecnica_nelumbo.domain.model.ParkingModel;
import com.ptn.prueba_tecnica_nelumbo.domain.model.UserModel;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IParkingPersistencePort;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration.Constants;

public class ParkingUseCase implements IParkingServicePort {

    private final IParkingPersistencePort iParkingPersistencePort;
    
    private final IUserServicePort iUserServicePort;

    public ParkingUseCase(IParkingPersistencePort iParkingPersistencePort,
    		IUserServicePort iUserServicePort) {
        this.iParkingPersistencePort = iParkingPersistencePort;
        this.iUserServicePort = iUserServicePort;
    }

	@Override
	public CreatedObjectResponseDto saveParking(ParkingModel parkingModel) {
		userExists(parkingModel.getUserModel().getId());
		validateUserRole(parkingModel.getUserModel().getId());
		nameExists(parkingModel.getName());
		parkingModel.setCreation(new Date()); 
		
		ParkingModel parkingModelDb = iParkingPersistencePort.saveParking(parkingModel);
		
		return new CreatedObjectResponseDto(parkingModelDb.getId());
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
	
	public void userExists(Long userId) {
		iUserServicePort.getUser(userId);
	}
	
	public void validateUserRole(Long userId) {
		UserModel userModel = iUserServicePort.getUser(userId);

		if(!userModel.getRoleModel().getRoleName().equals(Constants.ROLE_SOCIO)) {
			throw new BadRequestException("El usuario que desea asociar no posee el rol SOCIO.");
		}
	}
	
	public void nameExists(String name) {
		if(iParkingPersistencePort.getParkingByName(name) != null) {
			throw new BadRequestException("El name " + name + " ya existe. Ingrese uno diferente.");
		}
	}

}