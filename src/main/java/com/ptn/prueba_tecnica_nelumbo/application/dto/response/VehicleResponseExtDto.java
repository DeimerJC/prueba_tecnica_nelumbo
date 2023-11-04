package com.ptn.prueba_tecnica_nelumbo.application.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleResponseExtDto {
	
    private Long id;
    
    private ParkingResponseDto parkingResponseDto;
    
    private String plate;

    private String status;

    private Date dateAdmission;

    private Date creation;

}
