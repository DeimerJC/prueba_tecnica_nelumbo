 package com.ptn.prueba_tecnica_nelumbo.application.handler.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.ParkingHistoryRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.ParkingHistoryResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.handler.IParkingHistoryHandler;
import com.ptn.prueba_tecnica_nelumbo.application.mapper.IParkingHistoryRequestMapper;
import com.ptn.prueba_tecnica_nelumbo.application.mapper.IParkingHistoryResponseMapper;
import com.ptn.prueba_tecnica_nelumbo.domain.api.IParkingHistoryServicePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParkingHistoryHandler implements IParkingHistoryHandler {

    private final IParkingHistoryServicePort iParkingHistoryServicePort;
    private final IParkingHistoryRequestMapper iParkingHistoryRequestMapper;
    private final IParkingHistoryResponseMapper iParkingHistoryResponseMapper;

    @Transactional
	@Override
	public ParkingHistoryResponseDto saveParkingHistory(ParkingHistoryRequestDto parkingHistoryRequestDto) {
		return iParkingHistoryResponseMapper.toResponse(
					iParkingHistoryServicePort.saveParkingHistory(
						iParkingHistoryRequestMapper.toModel(parkingHistoryRequestDto)
					)
				);
    }

	@Override
	public List<ParkingHistoryResponseDto> getAllParkingHistorys() {
		return iParkingHistoryResponseMapper.toResponseList( iParkingHistoryServicePort.getAllParkingHistorys() );
    }

	@Override
	public ParkingHistoryResponseDto getParkingHistory(Long parkingHistoryId) {
		return iParkingHistoryResponseMapper.toResponse( iParkingHistoryServicePort.getParkingHistory(parkingHistoryId) );
	}

	@Transactional
	@Override
	public ParkingHistoryResponseDto updateParkingHistory(ParkingHistoryRequestDto parkingHistoryRequestDto) {
		return iParkingHistoryResponseMapper.toResponse( 
					iParkingHistoryServicePort.updateParkingHistory(
						iParkingHistoryRequestMapper.toModel(parkingHistoryRequestDto)
					) 
				);
	}

	@Transactional
	@Override
	public void deleteParkingHistory(Long parkingHistoryId) {
		iParkingHistoryServicePort.deleteParkingHistory(parkingHistoryId);
	}
	
}