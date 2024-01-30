package com.ptn.prueba_tecnica_nelumbo.infrastructure.exception;

public class BadGatewayException extends RuntimeException {
	
    private static final long serialVersionUID = 4982913246781781042L;

	public BadGatewayException() {
		super();
    }
	
	public BadGatewayException(String message) {
        super(message);
    }
    
}
