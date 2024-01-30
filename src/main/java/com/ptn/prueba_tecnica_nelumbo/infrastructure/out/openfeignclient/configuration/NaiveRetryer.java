package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.openfeignclient.configuration;

import com.ptn.prueba_tecnica_nelumbo.infrastructure.exception.BadGatewayException;

import feign.RetryableException;
import feign.Retryer;

public class NaiveRetryer implements feign.Retryer {
    
	@Override
    public void continueOrPropagate(RetryableException e) {
//        try {
//            Thread.sleep(1000L);
//        } catch (InterruptedException ex) {
//            Thread.currentThread().interrupt();
//            throw e;
//        }
		e.printStackTrace();
    	throw new BadGatewayException("El servidor encontró un error temporal y no pudo completar su solicitud.");
    }

    @Override
    public Retryer clone() {
        return new NaiveRetryer();
    }
	
}
