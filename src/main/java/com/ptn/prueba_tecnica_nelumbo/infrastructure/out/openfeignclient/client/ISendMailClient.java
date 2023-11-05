package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.openfeignclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ptn.prueba_tecnica_nelumbo.application.openfeigndto.response.MessageResponseDto;
import com.ptn.prueba_tecnica_nelumbo.application.openfeigndto.request.SendMailClientRequestDto;

@FeignClient(name = "${ms-envio-correo.name}", url = "${ms-envio-correo.url}")
//@Repository
public interface ISendMailClient {
	
	@PostMapping("/api/v1/send-mail")
	public MessageResponseDto registerIncome(@RequestBody SendMailClientRequestDto sendMailRequestDto);

}
