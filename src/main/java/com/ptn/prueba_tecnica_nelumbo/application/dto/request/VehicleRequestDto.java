package com.ptn.prueba_tecnica_nelumbo.application.dto.request;

import com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration.Constants;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleRequestDto {
	
	private Long id;
	
	@NotNull(message = Constants.FIELD_NOT_NULL)
    private Long parkingId;

	@NotNull(message = Constants.FIELD_NOT_NULL)
    @NotBlank(message = Constants.FIELD_NOT_BLANK)
    @Pattern(regexp = "^[A-Za-z0-9]{6}$", message = "The plate is invalid.")
    private String plate;

}
