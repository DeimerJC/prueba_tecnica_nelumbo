package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.ptn.prueba_tecnica_nelumbo.domain.model.RoleModel;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.entity.RoleEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRoleEntityMapper {

	RoleEntity toEntity(RoleModel roleModel);
	
	RoleModel toModel(RoleEntity roleEntity);
	
    List<RoleModel> toModelList(List<RoleEntity> roleEntitiesList);
}