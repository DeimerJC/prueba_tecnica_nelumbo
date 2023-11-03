package com.ptn.prueba_tecnica_nelumbo.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.VehicleRequestDto;
import com.ptn.prueba_tecnica_nelumbo.domain.model.VehicleModel;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IVehicleRequestMapper {

	@Mapping(target = "parkingModel.id", source = "vehicleRequestDto.parkingId")
    VehicleModel toModel(VehicleRequestDto vehicleRequestDto);
    
}
