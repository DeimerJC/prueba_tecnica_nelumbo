package com.ptn.prueba_tecnica_nelumbo.domain.exception;

public class ConflictException extends RuntimeException {
	
    private static final long serialVersionUID = 4982913246781781042L;

	public ConflictException() {
		super();
    }
	
	public ConflictException(String message) {
        super(message);
    }
    
}
