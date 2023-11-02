package com.ptn.prueba_tecnica_nelumbo.application.handler;

import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.RoleRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.RoleResponseDto;

public interface IRoleHandler {
	
	RoleResponseDto saveRole(RoleRequestDto roleRequestDto);

    List<RoleResponseDto> getAllRoles();

    RoleResponseDto getRole(Long roleId);

    RoleResponseDto updateRole(RoleRequestDto roleRequestDto);

    void deleteRole(Long roleId);

    RoleResponseDto getRoleByName(String name);
    
}