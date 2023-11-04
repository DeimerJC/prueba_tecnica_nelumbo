 package com.ptn.prueba_tecnica_nelumbo.application.handler.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.VehicleRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.VehicleResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.handler.IVehicleHandler;
import com.ptn.prueba_tecnica_nelumbo.application.mapper.IVehicleRequestMapper;
import com.ptn.prueba_tecnica_nelumbo.application.mapper.IVehicleResponseMapper;
import com.ptn.prueba_tecnica_nelumbo.domain.api.IVehicleServicePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleHandler implements IVehicleHandler {

    private final IVehicleServicePort iVehicleServicePort;
    private final IVehicleRequestMapper iVehicleRequestMapper;
    private final IVehicleResponseMapper iVehicleResponseMapper;

    @Transactional
	@Override
	public VehicleResponseDto saveVehicle(VehicleRequestDto vehicleRequestDto) {
		return iVehicleResponseMapper.toResponse(
					iVehicleServicePort.saveVehicle(
						iVehicleRequestMapper.toModel(vehicleRequestDto)
					)
				);
    }

	@Override
	public List<VehicleResponseDto> getAllVehicles() {
		return iVehicleResponseMapper.toResponseList( iVehicleServicePort.getAllVehicles() );
    }

	@Override
	public VehicleResponseDto getVehicle(Long vehicleId) {
		return iVehicleResponseMapper.toResponse( iVehicleServicePort.getVehicle(vehicleId) );
	}

	@Transactional
	@Override
	public VehicleResponseDto updateVehicle(VehicleRequestDto vehicleRequestDto) {
		return iVehicleResponseMapper.toResponse( 
					iVehicleServicePort.updateVehicle(
						iVehicleRequestMapper.toModel(vehicleRequestDto)
					) 
				);
	}

	@Transactional
	@Override
	public void deleteVehicle(Long vehicleId) {
		iVehicleServicePort.deleteVehicle(vehicleId);
	}

	@Transactional
	@Override
	public VehicleResponseDto registerIncome(VehicleRequestDto vehicleRequestDto) {
		return iVehicleResponseMapper.toResponse(
				iVehicleServicePort.registerIncome(
						iVehicleRequestMapper.toModel(vehicleRequestDto)
				)
			);
	}
	
}