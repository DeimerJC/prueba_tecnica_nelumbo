package com.ptn.prueba_tecnica_nelumbo.application.handler;

import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.UserRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.CreatedObjectResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.UserResponseDto;

public interface IUserHandler {

	CreatedObjectResponseDto saveUser(UserRequestDto userRequestDto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUser(Long userId);

    UserResponseDto updateUser(UserRequestDto userRequestDto);

    void deleteUser(Long userId);

    UserResponseDto getByUsername(String username); 
    
}