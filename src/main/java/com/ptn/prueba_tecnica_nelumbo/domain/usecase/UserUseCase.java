package com.ptn.prueba_tecnica_nelumbo.domain.usecase;

import java.util.Date;
import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.domain.exception.BadRequestException;
import com.ptn.prueba_tecnica_nelumbo.domain.exception.NoDataFoundException;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.CreatedObjectResponseDto;
import com.ptn.prueba_tecnica_nelumbo.domain.api.IRoleServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.api.IUserServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.model.UserModel;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IUserPersistencePort;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration.Constants;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort iUserPersistencePort;

    private final IRoleServicePort iRoleServicePort;

    public UserUseCase(IUserPersistencePort iUserPersistencePort,
    		IRoleServicePort iRoleServicePort) {
        this.iUserPersistencePort = iUserPersistencePort;
        this.iRoleServicePort = iRoleServicePort;
    }

	@Override
	public CreatedObjectResponseDto saveUser(UserModel userModel) {
		roleExists(userModel.getRoleModel().getId());
		emailExists(userModel.getEmail());
		identificationExists(userModel.getIdentification());
		validateStatus(userModel.getStatus()); 
		userModel.setCreation(new Date()); 
		
		UserModel userModelDb = iUserPersistencePort.saveUser(userModel);
		
		return new CreatedObjectResponseDto(userModelDb.getId());
	}

	@Override
	public List<UserModel> getAllUsers() {
		return iUserPersistencePort.getAllUsers();
	}

	@Override
	public UserModel getUser(Long userId) {
		UserModel userModel;
		userModel = iUserPersistencePort.getUser(userId);
		
		if(userModel == null) {
			throw new NoDataFoundException("No se encontró un usuario con el id "+userId);
		}
		
		return userModel;
	}

	@Override
	public UserModel updateUser(UserModel userModel) {
		
		getUser(userModel.getId());
		
		return iUserPersistencePort.updateUser(userModel);
	}

	@Override
	public void deleteUser(Long userId) {
		
		getUser(userId);
		
		iUserPersistencePort.deleteUser(userId);
	}

	@Override
	public UserModel getByUsername(String username) {
		return iUserPersistencePort.getByUsername(username);
	}
	
	public void roleExists(Long roleId) {
		if(iRoleServicePort.getRole(roleId) == null) {
			throw new NoDataFoundException("No se encontró un rol con el id " + roleId);
		}
	}
	
	public void emailExists(String email) {
		if(getByUsername(email) != null) {
			throw new BadRequestException("El email " + email + " ya existe. Ingrese uno diferente.");
		}
	}
	
	public void identificationExists(String identification) {
		if(iUserPersistencePort.getByIdentification(identification) != null) {
			throw new BadRequestException("La identification " + identification + " ya existe. Ingrese una diferente.");
		}
	}
	
	public void validateStatus(String status) {
		if(!status.equals(Constants.STATUS_ENABLE) && !status.equals(Constants.STATUS_DISABLE)) {
			throw new BadRequestException("El status " + status + " no es valido.");
		}
	}
	
}