package com.ptn.prueba_tecnica_nelumbo.domain.spi;

import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.domain.model.RoleModel;

public interface IRolePersistencePort {
	
	RoleModel saveRole(RoleModel roleModel);

    List<RoleModel> getAllRoles();

    RoleModel getRole(Long roleId);

    RoleModel updateRole(RoleModel roleModel);

    void deleteRole(Long roleId);

	RoleModel getRoleByName(String name); 
    
}