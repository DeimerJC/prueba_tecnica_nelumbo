package com.ptn.prueba_tecnica_nelumbo.domain.usecase;

import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.domain.api.IRoleServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.model.RoleModel;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IRolePersistencePort;

public class RoleUseCase implements IRoleServicePort {

    private final IRolePersistencePort iRolePersistencePort;

    public RoleUseCase(IRolePersistencePort iRolePersistencePort) {
        this.iRolePersistencePort = iRolePersistencePort;
    }

	@Override
	public RoleModel saveRole(RoleModel roleModel) {
		return iRolePersistencePort.saveRole(roleModel);
	}

	@Override
	public List<RoleModel> getAllRoles() {
		return iRolePersistencePort.getAllRoles();
	}

	@Override
	public RoleModel getRole(Long roleId) {
		return iRolePersistencePort.getRole(roleId);
	}

	@Override
	public RoleModel updateRole(RoleModel roleModel) {
		return iRolePersistencePort.updateRole(roleModel); 
	}

	@Override
	public void deleteRole(Long roleId) {
		iRolePersistencePort.deleteRole(roleId);
	}

	@Override
	public RoleModel getRoleByName(String name) {
		return iRolePersistencePort.getRoleByName(name);
	}

}