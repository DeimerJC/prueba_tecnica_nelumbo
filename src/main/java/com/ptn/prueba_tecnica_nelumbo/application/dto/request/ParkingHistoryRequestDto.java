package com.ptn.prueba_tecnica_nelumbo.application.dto.request;

import java.util.Date;

import com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration.Constants;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingHistoryRequestDto {
	
	private Long id;
	
	@NotNull(message = Constants.FIELD_NOT_NULL)
    private Long vehicleId;

	@NotNull(message = Constants.FIELD_NOT_NULL)
    private Long parkingId;
    
	@NotNull(message = Constants.FIELD_NOT_NULL)
    private Date departureDate;

}
