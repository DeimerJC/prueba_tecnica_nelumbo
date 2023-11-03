package com.ptn.prueba_tecnica_nelumbo.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.ParkingRequestDto;
import com.ptn.prueba_tecnica_nelumbo.domain.model.ParkingModel;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IParkingRequestMapper {

	@Mapping(target = "userModel.id", source = "parkingRequestDto.userId")
    ParkingModel toModel(ParkingRequestDto parkingRequestDto);
    
}
