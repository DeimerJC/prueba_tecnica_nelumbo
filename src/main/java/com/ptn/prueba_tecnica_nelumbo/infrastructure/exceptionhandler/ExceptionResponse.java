package com.ptn.prueba_tecnica_nelumbo.infrastructure.exceptionhandler;

public enum ExceptionResponse {
	
    NO_DATA_FOUND("No data found for the requested petition."),
    BAD_REQUEST("Invalid request, the request could not be interpreted."),
    CONFLICT("The request has a conflict with the server."),
	DOMAIN("An internal error has occurred."),
	BAD_GATEWAY("Bad Gateway.");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
    
}