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
	
}