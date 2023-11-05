package com.ptn.prueba_tecnica_nelumbo.domain.api;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.SendMailRequestDto;

public interface ISendMailServicePort {
	
	String sendMail(SendMailRequestDto sendMailRequestDto);   
    
}