package com.ptn.prueba_tecnica_nelumbo.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.ptn.prueba_tecnica_nelumbo.application.dto.response.ParkingResponseDto;
import com.ptn.prueba_tecnica_nelumbo.domain.model.ParkingModel;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IParkingResponseMapper {

	@Mapping(target = "userResponseDto.id", source = "parkingModel.userModel.id")
    ParkingResponseDto toResponse(ParkingModel parkingModel);

    List<ParkingResponseDto> toResponseList(List<ParkingModel> parkingModelList);
    
}
