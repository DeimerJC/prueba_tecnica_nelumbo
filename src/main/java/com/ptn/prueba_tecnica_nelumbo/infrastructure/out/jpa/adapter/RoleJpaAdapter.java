package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.adapter;

import java.util.Collections;
import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.domain.model.RoleModel;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IRolePersistencePort;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.entity.RoleEntity;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.repository.IRoleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleRepository iRoleRepository;
    private final IRoleEntityMapper iRoleEntityMapper;
    
	@Override
	public RoleModel saveRole(RoleModel roleModel) {
		return iRoleEntityMapper.toModel(iRoleRepository.save(iRoleEntityMapper.toEntity(roleModel)));
    }
	
	@Override
	public List<RoleModel> getAllRoles() {
		List<RoleEntity> entityList = iRoleRepository.findAll();
        if (entityList.isEmpty()) {
            Collections.emptyList();
        }
        return iRoleEntityMapper.toModelList(entityList);
    }
	
	@Override
	public RoleModel getRole(Long roleId) {
		return iRoleEntityMapper.toModel(iRoleRepository.findById(roleId)
        		.orElse(null));
	}
	
	@Override
	public RoleModel updateRole(RoleModel roleModel) {
		return iRoleEntityMapper.toModel(iRoleRepository.save(iRoleEntityMapper.toEntity(roleModel)));
    }
	
	@Override
	public void deleteRole(Long roleId) {
		iRoleRepository.deleteById(roleId);
	}

	@Override
	public RoleModel getRoleByName(String name) {
		return iRoleEntityMapper.toModel(iRoleRepository.findByRoleName(name));
	}

}