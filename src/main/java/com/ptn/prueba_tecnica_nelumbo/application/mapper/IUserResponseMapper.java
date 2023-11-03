package com.ptn.prueba_tecnica_nelumbo.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.ptn.prueba_tecnica_nelumbo.application.dto.response.UserResponseDto;
import com.ptn.prueba_tecnica_nelumbo.domain.model.UserModel;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {
	
	@Mapping(target = "roleResponseDto.id", source = "userModel.roleModel.id")
    UserResponseDto toResponse(UserModel userModel);

    List<UserResponseDto> toResponseList(List<UserModel> userModelList);
    
}
