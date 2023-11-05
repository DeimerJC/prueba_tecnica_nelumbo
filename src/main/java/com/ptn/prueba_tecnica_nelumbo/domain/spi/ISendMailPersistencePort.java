package com.ptn.prueba_tecnica_nelumbo.domain.spi;

import com.ptn.prueba_tecnica_nelumbo.application.openfeigndto.request.SendMailClientRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.openfeigndto.response.MessageResponseDto;

public interface ISendMailPersistencePort {
	
	MessageResponseDto sendMail(SendMailClientRequestDto sendMailRequestDto);   
    
}