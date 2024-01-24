 package com.ptn.prueba_tecnica_nelumbo.application.handler.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.ParkingRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.CreatedObjectResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.ParkingResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.handler.IParkingHandler;
import com.ptn.prueba_tecnica_nelumbo.application.mapper.IParkingRequestMapper;
import com.ptn.prueba_tecnica_nelumbo.application.mapper.IParkingResponseMapper;
import com.ptn.prueba_tecnica_nelumbo.domain.api.IParkingServicePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParkingHandler implements IParkingHandler {

    private final IParkingServicePort iParkingServicePort;
    private final IParkingRequestMapper iParkingRequestMapper;
    private final IParkingResponseMapper iParkingResponseMapper;

    @Transactional
	@Override
	public CreatedObjectResponseDto saveParking(ParkingRequestDto parkingRequestDto) {
		return iParkingServicePort.saveParking(iParkingRequestMapper.toModel(parkingRequestDto));
	}

	@Override
	public List<ParkingResponseDto> getAllParkings() {
		return iParkingResponseMapper.toResponseList( iParkingServicePort.getAllParkings() );
    }

	@Override
	public ParkingResponseDto getParking(Long parkingId) {
		return iParkingResponseMapper.toResponse( iParkingServicePort.getParking(parkingId) );
	}

	@Transactional
	@Override
	public ParkingResponseDto updateParking(ParkingRequestDto parkingRequestDto) {
		return iParkingResponseMapper.toResponse( 
					iParkingServicePort.updateParking(
						iParkingRequestMapper.toModel(parkingRequestDto)
					) 
				);
	}

	@Transactional
	@Override
	public void deleteParking(Long parkingId) {
		iParkingServicePort.deleteParking(parkingId);
	}

}