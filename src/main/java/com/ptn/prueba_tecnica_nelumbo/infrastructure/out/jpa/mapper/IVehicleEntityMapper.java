package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.ptn.prueba_tecnica_nelumbo.domain.model.VehicleModel;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.entity.VehicleEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IVehicleEntityMapper {

	@Mapping(target = "parkingEntity.id", source = "vehicleModel.parkingModel.id")
	VehicleEntity toEntity(VehicleModel vehicleModel);

	@Mapping(target = "parkingModel.id", source = "vehicleEntity.parkingEntity.id")
	VehicleModel toModel(VehicleEntity vehicleEntity);
	
    List<VehicleModel> toModelList(List<VehicleEntity> vehicleEntitiesList);
}