 package com.ptn.prueba_tecnica_nelumbo.application.handler.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public Double parkingProfitsDay(Long parkingId) {
		return iParkingHistoryServicePort.parkingProfitsDay(parkingId);
	}

	@Override
	public Double parkingProfitsWeek(Long parkingId) {
		return iParkingHistoryServicePort.parkingProfitsWeek(parkingId);
	}

	@Override
	public Double parkingProfitsMonth(Long parkingId) {
		return iParkingHistoryServicePort.parkingProfitsMonth(parkingId);
	}

	@Override
	public Double parkingProfitsYear(Long parkingId) {
		return iParkingHistoryServicePort.parkingProfitsYear(parkingId);
	}

	@Override
	public List<Map<String, Long>> mostRegisteredVehicles() {
		List<Object[]> results = iParkingHistoryServicePort.mostRegisteredVehicles();
	    List<Map<String, Long>> resultMap = new ArrayList<>();
	    Map<String, Long> map;
	    for (Object[] result : results) {
	    	map = new HashMap<>();
	        Long id = ((Number) result[0]).longValue();
	        Long amount = ((Number) result[1]).longValue();
	        map.put("id", id);
	        map.put("amount", amount);
	        resultMap.add(map);
	    }
		return resultMap;
	}

	@Override
	public List<Map<String, Long>> mostRegisteredVehiclesByParking(Long parkingId) {
		List<Object[]> results = iParkingHistoryServicePort.mostRegisteredVehiclesByParking(parkingId);
	    List<Map<String, Long>> resultMap = new ArrayList<>();
	    Map<String, Long> map;
	    for (Object[] result : results) {
	    	map = new HashMap<>();
	        Long id = ((Number) result[0]).longValue();
	        Long amount = ((Number) result[1]).longValue();
	        map.put("id", id);
	        map.put("amount", amount);
	        resultMap.add(map);
	    }
		return resultMap;
	}
	
}