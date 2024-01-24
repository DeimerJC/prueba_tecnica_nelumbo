package com.ptn.prueba_tecnica_nelumbo.domain.spi;

import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.domain.model.UserModel;

public interface IUserPersistencePort {
	
	UserModel saveUser(UserModel userModel);

    List<UserModel> getAllUsers();

    UserModel getUser(Long userId);

    UserModel updateUser(UserModel userModel);

    void deleteUser(Long userId);

	UserModel getByUsername(String username); 
	
	UserModel getByIdentification(String username); 
    
}