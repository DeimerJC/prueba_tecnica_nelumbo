package com.ptn.prueba_tecnica_nelumbo.application.dto.request;

import com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration.Constants;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingRequestDto {
	
	private Long id;
	
	@NotNull(message = Constants.FIELD_NOT_NULL)
    private Long userId;

	@NotNull(message = Constants.FIELD_NOT_NULL)
    @NotBlank(message = Constants.FIELD_NOT_BLANK)
    private String name;
    
	@NotNull(message = Constants.FIELD_NOT_NULL)
    private Integer vehicleLimit;
	
	@NotNull(message = Constants.FIELD_NOT_NULL)
    private Double valueHour;

}
