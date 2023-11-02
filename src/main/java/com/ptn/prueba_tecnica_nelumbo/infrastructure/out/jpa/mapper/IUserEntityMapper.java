package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.ptn.prueba_tecnica_nelumbo.domain.model.UserModel;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.entity.UserEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserEntityMapper {

	@Mapping(target = "dependenceEntity.id", source = "userModel.dependenceModel.id")
	@Mapping(target = "roleEntity.id", source = "userModel.roleModel.id")
    UserEntity toEntity(UserModel userModel);
	
	@Mapping(target = "dependenceModel.id", source = "userEntity.dependenceEntity.id")
	@Mapping(target = "roleModel.id", source = "userEntity.roleEntity.id")
    UserModel toModel(UserEntity userEntity);
	
    List<UserModel> toModelList(List<UserEntity> userEntityList);
}