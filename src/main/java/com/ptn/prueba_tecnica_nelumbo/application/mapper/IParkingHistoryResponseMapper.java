package com.ptn.prueba_tecnica_nelumbo.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.ptn.prueba_tecnica_nelumbo.application.dto.response.ParkingHistoryResponseDto;
import com.ptn.prueba_tecnica_nelumbo.domain.model.ParkingHistoryModel;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IParkingHistoryResponseMapper {

	@Mapping(target = "vehicleResponseDto.id", source = "parkingHistoryModel.vehicleModel.id")
	@Mapping(target = "parkingResponseDto.id", source = "parkingHistoryModel.parkingModel.id")
    ParkingHistoryResponseDto toResponse(ParkingHistoryModel parkingHistoryModel);

    List<ParkingHistoryResponseDto> toResponseList(List<ParkingHistoryModel> parkingHistoryModelList);
    
}
