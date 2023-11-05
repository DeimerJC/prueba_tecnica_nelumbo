package com.ptn.prueba_tecnica_nelumbo.application.handler;

import java.util.List;
import java.util.Map;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.ParkingHistoryRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.ParkingHistoryResponseDto;

public interface IParkingHistoryHandler {
	
	ParkingHistoryResponseDto saveParkingHistory(ParkingHistoryRequestDto parkingHistoryRequestDto);

    List<ParkingHistoryResponseDto> getAllParkingHistorys();

    ParkingHistoryResponseDto getParkingHistory(Long parkingHistoryId);

    ParkingHistoryResponseDto updateParkingHistory(ParkingHistoryRequestDto parkingHistoryRequestDto);

    void deleteParkingHistory(Long parkingHistoryId);

	Double parkingProfitsDay(Long parkingId);

	Double parkingProfitsWeek(Long parkingId);   

	Double parkingProfitsMonth(Long parkingId);   

	Double parkingProfitsYear(Long parkingId);

	List<Map<String, Long>> mostRegisteredVehicles();

	List<Map<String, Long>> mostRegisteredVehiclesByParking(Long parkingId);   

}