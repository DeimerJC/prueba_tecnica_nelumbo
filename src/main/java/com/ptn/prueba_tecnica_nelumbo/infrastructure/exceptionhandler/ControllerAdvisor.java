package com.ptn.prueba_tecnica_nelumbo.infrastructure.exceptionhandler;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ptn.prueba_tecnica_nelumbo.domain.exception.BadRequestException;
import com.ptn.prueba_tecnica_nelumbo.domain.exception.ConflictException;
import com.ptn.prueba_tecnica_nelumbo.domain.exception.DomainException;
import com.ptn.prueba_tecnica_nelumbo.domain.exception.NoDataFoundException;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE,
                        ignoredNoDataFoundException.getMessage() != null ? ignoredNoDataFoundException.getMessage() : ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }
    
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, String>> handleBadRequestException(
    		BadRequestException ignoredBadRequestException) {
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    			.body(Collections.singletonMap(MESSAGE,
    					ignoredBadRequestException.getMessage() != null ? ignoredBadRequestException.getMessage() : ExceptionResponse.BAD_REQUEST.getMessage()));
    }
    
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Map<String, String>> handleConflictException(
    		ConflictException ignoredConflictException) {
    	return ResponseEntity.status(HttpStatus.CONFLICT)
    			.body(Collections.singletonMap(MESSAGE,
    					ignoredConflictException.getMessage() != null ? ignoredConflictException.getMessage() : ExceptionResponse.CONFLICT.getMessage()));
    }
    
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Map<String, String>> handleDomainException(
    		DomainException ignoredDomainException) {
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    			.body(Collections.singletonMap(MESSAGE,
    					ignoredDomainException.getMessage() != null ? ignoredDomainException.getMessage() : ExceptionResponse.DOMAIN.getMessage()));
    }
    
}