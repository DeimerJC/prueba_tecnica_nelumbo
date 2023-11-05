package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.openfeignclient.adapter;

import com.ptn.prueba_tecnica_nelumbo.application.openfeigndto.request.SendMailClientRequestDto;
import com.ptn.prueba_tecnica_nelumbo.application.openfeigndto.response.MessageResponseDto;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.ISendMailPersistencePort;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.openfeignclient.client.ISendMailClient;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SendMailAdapter implements ISendMailPersistencePort {

    private final ISendMailClient iSendMailClient;

	@Override
	public MessageResponseDto sendMail(SendMailClientRequestDto sendMailRequestDto) {
		return iSendMailClient.registerIncome(sendMailRequestDto);
	}
    
}