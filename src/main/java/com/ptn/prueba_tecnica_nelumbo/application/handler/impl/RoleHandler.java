 package com.ptn.prueba_tecnica_nelumbo.application.handler.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.RoleRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.RoleResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.handler.IRoleHandler;
import com.ptn.prueba_tecnica_nelumbo.application.mapper.IRoleRequestMapper;
import com.ptn.prueba_tecnica_nelumbo.application.mapper.IRoleResponseMapper;
import com.ptn.prueba_tecnica_nelumbo.domain.api.IRoleServicePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleHandler implements IRoleHandler {

    private final IRoleServicePort iRoleServicePort;
    private final IRoleRequestMapper iRoleRequestMapper;
    private final IRoleResponseMapper iRoleResponseMapper;

    @Transactional
	@Override
	public RoleResponseDto saveRole(RoleRequestDto roleRequestDto) {
		return iRoleResponseMapper.toResponse(
					iRoleServicePort.saveRole(
						iRoleRequestMapper.toModel(roleRequestDto)
					)
				);
    }

	@Override
	public List<RoleResponseDto> getAllRoles() {
		return iRoleResponseMapper.toResponseList( iRoleServicePort.getAllRoles() );
    }

	@Override
	public RoleResponseDto getRole(Long roleId) {
		return iRoleResponseMapper.toResponse( iRoleServicePort.getRole(roleId) );
	}

	@Transactional
	@Override
	public RoleResponseDto updateRole(RoleRequestDto roleRequestDto) {
		return iRoleResponseMapper.toResponse( 
					iRoleServicePort.updateRole(
						iRoleRequestMapper.toModel(roleRequestDto)
					) 
				);
	}

	@Transactional
	@Override
	public void deleteRole(Long roleId) {
		iRoleServicePort.deleteRole(roleId);
	}

	@Override
	public RoleResponseDto getRoleByName(String name) {
		return iRoleResponseMapper.toResponse( iRoleServicePort.getRoleByName(name) );
	}
}