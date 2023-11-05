package com.ptn.prueba_tecnica_nelumbo.application.handler;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.SendMailRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.MessageResponseDto;

public interface ISendMailHandler {
	
	MessageResponseDto sendMail(SendMailRequestDto sendMailRequestDto);  

}