package com.ptn.prueba_tecnica_nelumbo.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.ParkingHistoryRequestDto;
import com.ptn.prueba_tecnica_nelumbo.domain.model.ParkingHistoryModel;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IParkingHistoryRequestMapper {

	@Mapping(target = "vehicleModel.id", source = "parkingHistoryRequestDto.vehicleId")
	@Mapping(target = "parkingModel.id", source = "parkingHistoryRequestDto.parkingId")
    ParkingHistoryModel toModel(ParkingHistoryRequestDto parkingHistoryRequestDto);
    
}
