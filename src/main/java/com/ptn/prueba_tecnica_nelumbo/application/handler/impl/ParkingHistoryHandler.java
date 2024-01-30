 package com.ptn.prueba_tecnica_nelumbo.application.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.ParkingHistoryRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.MostRegisteredVehiclesParkingResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.MostRegisteredVehiclesResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.ParkingHistoryResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.ProfitsIndicatorsResponseDto;
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
	public ProfitsIndicatorsResponseDto parkingProfitsYear(Long parkingId) {
		return new ProfitsIndicatorsResponseDto(iParkingHistoryServicePort.parkingProfitsYear(parkingId));
	}

	@Override
	public List<MostRegisteredVehiclesResponseDto> mostRegisteredVehicles() {
		List<Object[]> results = iParkingHistoryServicePort.mostRegisteredVehicles();
		List<MostRegisteredVehiclesResponseDto> resultMap = new ArrayList<>();
		MostRegisteredVehiclesResponseDto mostRegisteredVehiclesResponseDto;
	    
	    for (Object[] result : results) {
	    	mostRegisteredVehiclesResponseDto = new MostRegisteredVehiclesResponseDto();
	        Long id = ((Number) result[0]).longValue();
	        Long amount = ((Number) result[1]).longValue();
	        String plate = ((String) result[2]);
	        
	        mostRegisteredVehiclesResponseDto.setVehicleId(id);
	        mostRegisteredVehiclesResponseDto.setAmount(amount);
	        mostRegisteredVehiclesResponseDto.setPlate(plate);
	        resultMap.add(mostRegisteredVehiclesResponseDto);
	    }
		return resultMap;
	}

	@Override
	public List<MostRegisteredVehiclesParkingResponseDto> mostRegisteredVehiclesByParking(Long parkingId) {
		List<Object[]> results = iParkingHistoryServicePort.mostRegisteredVehiclesByParking(parkingId);
	    List<MostRegisteredVehiclesParkingResponseDto> resultMap = new ArrayList<>();
	    MostRegisteredVehiclesParkingResponseDto mostRegisteredVehiclesResponseDto;
	    
	    for (Object[] result : results) {
	    	mostRegisteredVehiclesResponseDto = new MostRegisteredVehiclesParkingResponseDto();
	        Long id = ((Number) result[0]).longValue();
	        Long amount = ((Number) result[1]).longValue();
	        String parkingName = ((String) result[2]);
	        String plate = ((String) result[3]);
	        
	        mostRegisteredVehiclesResponseDto.setVehicleId(id);
	        mostRegisteredVehiclesResponseDto.setAmount(amount);
	        mostRegisteredVehiclesResponseDto.setParkingName(parkingName);
	        mostRegisteredVehiclesResponseDto.setPlate(plate);
	        resultMap.add(mostRegisteredVehiclesResponseDto);
	    }
		return resultMap;
	}
	
}