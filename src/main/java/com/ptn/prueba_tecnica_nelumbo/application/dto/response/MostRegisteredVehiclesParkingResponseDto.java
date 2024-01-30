package com.ptn.prueba_tecnica_nelumbo.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MostRegisteredVehiclesParkingResponseDto {
	
    private Long vehicleId;
    
    private Long amount;
    
    private String parkingName;
    
    private String plate;

}
