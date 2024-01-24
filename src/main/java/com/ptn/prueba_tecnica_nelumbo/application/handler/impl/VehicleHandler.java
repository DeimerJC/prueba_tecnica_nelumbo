 package com.ptn.prueba_tecnica_nelumbo.application.handler.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.VehicleExitRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.request.VehicleRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.MessageResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.VehicleResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.VehicleResponseExtDto;
import com.ptn.prueba_tecnica_nelumbo.application.handler.IVehicleHandler;
import com.ptn.prueba_tecnica_nelumbo.application.mapper.IVehicleRequestMapper;
import com.ptn.prueba_tecnica_nelumbo.application.mapper.IVehicleResponseMapper;
import com.ptn.prueba_tecnica_nelumbo.domain.api.IVehicleServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.model.ParkingModel;
import com.ptn.prueba_tecnica_nelumbo.domain.model.VehicleModel;

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
	public List<VehicleResponseExtDto> getAllVehicles(Long idParking, String token) {
		return iVehicleResponseMapper.toResponseListExt( iVehicleServicePort.getAllVehicles(idParking, token) );
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
	public VehicleResponseDto registerIncome(Long parkingId, VehicleRequestDto vehicleRequestDto, String token) {
		VehicleModel vehicleModel = iVehicleRequestMapper.toModel(vehicleRequestDto);
		vehicleModel.setParkingModel(new ParkingModel());
		vehicleModel.getParkingModel().setId(parkingId);
		
		return iVehicleResponseMapper.toResponse(
				iVehicleServicePort.registerIncome(
						vehicleModel, token
				)
			);
	}

	@Transactional
	@Override
	public MessageResponseDto checkOut(VehicleExitRequestDto vehicleExitRequestDto) {
		return iVehicleServicePort.checkOut(
						iVehicleRequestMapper.toModel(vehicleExitRequestDto)
				);
	}

	@Override
	public List<VehicleResponseExtDto> vehiclesByCoincidence(String plateSearch) {
		return iVehicleResponseMapper.toResponseListExt(
				iVehicleServicePort.vehiclesByCoincidence(plateSearch)
			);
	}

	@Override
	public List<VehicleResponseExtDto> vehiclesParkedFirstTime() {
		return iVehicleResponseMapper.toResponseListExt(
				iVehicleServicePort.vehiclesParkedFirstTime()
			);
	}
	
}