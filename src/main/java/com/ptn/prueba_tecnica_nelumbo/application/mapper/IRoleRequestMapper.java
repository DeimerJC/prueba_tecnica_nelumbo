package com.ptn.prueba_tecnica_nelumbo.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.RoleRequestDto;
import com.ptn.prueba_tecnica_nelumbo.domain.model.RoleModel;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleRequestMapper {
	
    RoleModel toModel(RoleRequestDto roleRequestDto);
    
}
