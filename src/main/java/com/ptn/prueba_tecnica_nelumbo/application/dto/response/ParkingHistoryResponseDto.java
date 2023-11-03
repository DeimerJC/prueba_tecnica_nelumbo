package com.ptn.prueba_tecnica_nelumbo.application.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingHistoryResponseDto {
	
    private Long id;
    
    private VehicleResponseDto vehicleResponseDto;
    
    private ParkingResponseDto parkingResponseDto;
    
    private Date departureDate;

    private Date creation;

}
