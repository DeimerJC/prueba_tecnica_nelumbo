package com.ptn.prueba_tecnica_nelumbo.application.handler;

import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.ParkingHistoryRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.MostRegisteredVehiclesResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.ParkingHistoryResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.ProfitsIndicatorsResponseDto;

public interface IParkingHistoryHandler {
	
	ParkingHistoryResponseDto saveParkingHistory(ParkingHistoryRequestDto parkingHistoryRequestDto);

    List<ParkingHistoryResponseDto> getAllParkingHistorys();

    ParkingHistoryResponseDto getParkingHistory(Long parkingHistoryId);

    ParkingHistoryResponseDto updateParkingHistory(ParkingHistoryRequestDto parkingHistoryRequestDto);

    void deleteParkingHistory(Long parkingHistoryId);

	Double parkingProfitsDay(Long parkingId);

	Double parkingProfitsWeek(Long parkingId);   

	Double parkingProfitsMonth(Long parkingId);   

	ProfitsIndicatorsResponseDto parkingProfitsYear(Long parkingId);

	List<MostRegisteredVehiclesResponseDto> mostRegisteredVehicles();

	List<MostRegisteredVehiclesResponseDto> mostRegisteredVehiclesByParking(Long parkingId);   

}