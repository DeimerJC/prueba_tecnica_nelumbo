package com.ptn.prueba_tecnica_nelumbo.domain.exception;

public class NoDataFoundException extends RuntimeException {
	
    private static final long serialVersionUID = 4982913246781781042L;

	public NoDataFoundException() {
		super();
    }
	
	public NoDataFoundException(String message) {
        super(message);
    }
    
}
