package com.ptn.prueba_tecnica_nelumbo.domain.api;

import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.domain.model.UserModel;

public interface IUserServicePort {

	UserModel saveUser(UserModel userModel);

    List<UserModel> getAllUsers();

    UserModel getUser(Long userId);

    UserModel updateUser(UserModel userModel);

    void deleteUser(Long userId);

	UserModel getByUsername(String username); 
    
}