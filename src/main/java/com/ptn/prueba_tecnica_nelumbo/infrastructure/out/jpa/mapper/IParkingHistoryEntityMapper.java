package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.ptn.prueba_tecnica_nelumbo.domain.model.ParkingHistoryModel;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.entity.ParkingHistoryEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IParkingHistoryEntityMapper {

	@Mapping(target = "vehicleEntity.id", source = "parkingHistoryModel.vehicleModel.id")
	@Mapping(target = "parkingEntity.id", source = "parkingHistoryModel.parkingModel.id")
	ParkingHistoryEntity toEntity(ParkingHistoryModel parkingHistoryModel);

	@Mapping(target = "vehicleModel.id", source = "parkingHistoryEntity.vehicleEntity.id")
	@Mapping(target = "parkingModel.id", source = "parkingHistoryEntity.parkingEntity.id")
	ParkingHistoryModel toModel(ParkingHistoryEntity parkingHistoryEntity);
	
    List<ParkingHistoryModel> toModelList(List<ParkingHistoryEntity> parkingHistoryEntitiesList);
}