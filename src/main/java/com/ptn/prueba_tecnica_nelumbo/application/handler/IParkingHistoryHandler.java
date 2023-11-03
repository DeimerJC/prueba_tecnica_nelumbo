package com.ptn.prueba_tecnica_nelumbo.application.handler;

import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.ParkingHistoryRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.ParkingHistoryResponseDto;

public interface IParkingHistoryHandler {
	
	ParkingHistoryResponseDto saveParkingHistory(ParkingHistoryRequestDto parkingHistoryRequestDto);

    List<ParkingHistoryResponseDto> getAllParkingHistorys();

    ParkingHistoryResponseDto getParkingHistory(Long parkingHistoryId);

    ParkingHistoryResponseDto updateParkingHistory(ParkingHistoryRequestDto parkingHistoryRequestDto);

    void deleteParkingHistory(Long parkingHistoryId);

}