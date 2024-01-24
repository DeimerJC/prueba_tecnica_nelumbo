package com.ptn.prueba_tecnica_nelumbo.application.dto.request;

import com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration.Constants;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingRequestDto {
	
//	private Long id;

	@NotNull(message = Constants.FIELD_NOT_NULL)
	@Digits(integer = 9999, fraction = 0, message = Constants.FIELD_INTEGER)
    @Positive(message = Constants.FIELD_INTEGER_POSITIVE)
    private Long userId;

	@NotNull(message = Constants.FIELD_NOT_NULL)
    @NotBlank(message = Constants.FIELD_NOT_BLANK)
    private String name;
    
	@NotNull(message = Constants.FIELD_NOT_NULL)
	@Digits(integer = 9999, fraction = 0, message = Constants.FIELD_INTEGER)
    @Positive(message = Constants.FIELD_INTEGER_POSITIVE)
    private Integer vehicleLimit;
	
	@NotNull(message = Constants.FIELD_NOT_NULL)
	@Digits(integer = 9999, fraction = 0, message = Constants.FIELD_INTEGER)
    @Positive(message = Constants.FIELD_INTEGER_POSITIVE)
    private Double valueHour;

}
