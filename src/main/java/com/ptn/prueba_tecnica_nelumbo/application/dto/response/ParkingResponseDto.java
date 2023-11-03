package com.ptn.prueba_tecnica_nelumbo.application.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingResponseDto {
	
    private Long id;
    
    private UserResponseDto userResponseDto;
    
    private String name;
    
    private Integer vehicleLimit;

    private Double valueHour;

    private Date creation;

}
