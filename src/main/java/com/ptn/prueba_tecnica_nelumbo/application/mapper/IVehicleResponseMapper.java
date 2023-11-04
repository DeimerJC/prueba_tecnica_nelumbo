package com.ptn.prueba_tecnica_nelumbo.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.ptn.prueba_tecnica_nelumbo.application.dto.response.VehicleResponseDto;
import com.ptn.prueba_tecnica_nelumbo.domain.model.VehicleModel;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IVehicleResponseMapper {

//	@Mapping(target = "parkingResponseDto.id", source = "vehicleModel.parkingModel.id")
    VehicleResponseDto toResponse(VehicleModel vehicleModel);

    List<VehicleResponseDto> toResponseList(List<VehicleModel> vehicleModelList);
    
}
