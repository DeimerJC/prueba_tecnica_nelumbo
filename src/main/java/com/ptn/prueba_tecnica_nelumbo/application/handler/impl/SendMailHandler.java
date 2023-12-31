 package com.ptn.prueba_tecnica_nelumbo.application.handler.impl;

import org.springframework.stereotype.Service;

import com.ptn.prueba_tecnica_nelumbo.application.dto.request.SendMailRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.dto.response.MessageResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.handler.ISendMailHandler;
import com.ptn.prueba_tecnica_nelumbo.domain.api.ISendMailServicePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SendMailHandler implements ISendMailHandler {

    private final ISendMailServicePort iSendMailServicePort;
    
	@Override
	public MessageResponseDto sendMail(SendMailRequestDto sendMailRequestDto) {
		return new MessageResponseDto(iSendMailServicePort.sendMail(sendMailRequestDto));
	}

}