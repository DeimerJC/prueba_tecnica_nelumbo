 package com.ptn.prueba_tecnica_nelumbo.application.handler.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.UserRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.CreatedObjectResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.UserResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.handler.IUserHandler;
import com.ptn.prueba_tecnica_nelumbo.application.mapper.IUserRequestMapper;
import com.ptn.prueba_tecnica_nelumbo.application.mapper.IUserResponseMapper;
import com.ptn.prueba_tecnica_nelumbo.domain.api.IUserServicePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserHandler implements IUserHandler {

    private final IUserServicePort iUserServicePort;
    private final IUserRequestMapper iUserRequestMapper;
    private final IUserResponseMapper iUserResponseMapper;

    @Transactional
	@Override
	public CreatedObjectResponseDto saveUser(UserRequestDto userRequestDto) {
		return iUserServicePort.saveUser(iUserRequestMapper.toModel(userRequestDto));
	}

	@Override
	public List<UserResponseDto> getAllUsers() {
		return iUserResponseMapper.toResponseList( iUserServicePort.getAllUsers() );
    }

	@Override
	public UserResponseDto getUser(Long userId) {
		return iUserResponseMapper.toResponse( iUserServicePort.getUser(userId) );
	}

	@Transactional
	@Override
	public UserResponseDto updateUser(UserRequestDto userRequestDto) {
		return iUserResponseMapper.toResponse( 
					iUserServicePort.updateUser(
						iUserRequestMapper.toModel(userRequestDto)
					) 
				);
	}

	@Transactional
	@Override
	public void deleteUser(Long userId) {
		iUserServicePort.deleteUser(userId);
	}

	@Override
	public UserResponseDto getByUsername(String username) {
		return iUserResponseMapper.toResponse( iUserServicePort.getByUsername(username) );
	}
}