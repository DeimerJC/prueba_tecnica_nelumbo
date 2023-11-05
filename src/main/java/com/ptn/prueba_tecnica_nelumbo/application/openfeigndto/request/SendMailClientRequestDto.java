package com.ptn.prueba_tecnica_nelumbo.application.openfeigndto.request;

import com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration.Constants;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMailClientRequestDto {
	
	@NotNull(message = Constants.FIELD_NOT_NULL)
    @NotBlank(message = Constants.FIELD_NOT_BLANK)
    private String email;
	
	@NotNull(message = Constants.FIELD_NOT_NULL)
	@NotBlank(message = Constants.FIELD_NOT_BLANK)
	private String plate;
	
	@NotNull(message = Constants.FIELD_NOT_NULL)
	@NotBlank(message = Constants.FIELD_NOT_BLANK)
	private String message;
	
	@NotNull(message = Constants.FIELD_NOT_NULL)
	@NotBlank(message = Constants.FIELD_NOT_BLANK)
	private String parkingName;

}
