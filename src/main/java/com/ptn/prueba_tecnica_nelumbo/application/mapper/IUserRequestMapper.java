package com.ptn.prueba_tecnica_nelumbo.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.UserRequestDto;
import com.ptn.prueba_tecnica_nelumbo.domain.model.UserModel;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {
	
	@Mapping(target = "dependenceModel.id", source = "userRequestDto.dependenceId")
	@Mapping(target = "roleModel.id", source = "userRequestDto.roleId")
    UserModel toModel(UserRequestDto userRequestDto);
    
}
