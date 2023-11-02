package com.ptn.prueba_tecnica_nelumbo.domain.usecase;

import java.util.Date;
import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.domain.exception.NoDataFoundException;
import com.ptn.prueba_tecnica_nelumbo.domain.api.IUserServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.model.UserModel;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IUserPersistencePort;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort iUserPersistencePort;

    public UserUseCase(IUserPersistencePort iUserPersistencePort) {
        this.iUserPersistencePort = iUserPersistencePort;
    }

	@Override
	public UserModel saveUser(UserModel userModel) {
		userModel.setCreation(new Date()); 
		return iUserPersistencePort.saveUser(userModel);
	}

	@Override
	public List<UserModel> getAllUsers() {
		return iUserPersistencePort.getAllUsers();
	}

	@Override
	public UserModel getUser(Long userId) {
		return iUserPersistencePort.getUser(userId);
	}

	@Override
	public UserModel updateUser(UserModel userModel) {
		
		if(getUser(userModel.getId()) == null) {
			throw new NoDataFoundException("No se encontró un usuario para actualizar.");
		}
		
		return iUserPersistencePort.updateUser(userModel);
	}

	@Override
	public void deleteUser(Long userId) {
		
		if(getUser(userId) == null) {
			throw new NoDataFoundException("No se encontró un usuario para eliminar.");
		}
		
		iUserPersistencePort.deleteUser(userId);
	}

	@Override
	public UserModel getByUsername(String username) {
		return iUserPersistencePort.getByUsername(username);
	}
	
}