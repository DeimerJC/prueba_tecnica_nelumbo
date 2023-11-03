package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.ptn.prueba_tecnica_nelumbo.domain.model.ParkingModel;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.entity.ParkingEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IParkingEntityMapper {

	@Mapping(target = "userEntity.id", source = "parkingModel.userModel.id")
	ParkingEntity toEntity(ParkingModel parkingModel);

	@Mapping(target = "userModel.id", source = "parkingEntity.userEntity.id")
	ParkingModel toModel(ParkingEntity parkingEntity);
	
    List<ParkingModel> toModelList(List<ParkingEntity> parkingEntitiesList);
}