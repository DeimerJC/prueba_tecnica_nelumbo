package com.ptn.prueba_tecnica_nelumbo.domain.exception;

public class DomainException extends RuntimeException {
	
	private static final long serialVersionUID = 6647430086570198892L;

	public DomainException() {
        super();
    }

    public DomainException(String message) {
        super(message);
    }
    
}
