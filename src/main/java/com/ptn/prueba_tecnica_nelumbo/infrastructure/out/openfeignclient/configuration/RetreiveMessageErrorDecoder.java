package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.openfeignclient.configuration;

import static feign.FeignException.errorStatus;

import com.ptn.prueba_tecnica_nelumbo.infrastructure.exception.BadGatewayException;

import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

public class RetreiveMessageErrorDecoder implements ErrorDecoder {
    
    @Override
    public Exception decode(String methodKey, Response response) {
	    FeignException exception = feign.FeignException.errorStatus(methodKey, response);
	    int status = response.status();
//        if (status >= 500) {
//            return new RetryableException(
//              response.status(),
//              exception.getMessage(),
//              response.request().httpMethod(),
//              exception,
//              null,
//              response.request());
//        }
        if (status >= 500) {
            return new BadGatewayException("El servidor encontró un error temporal y no pudo completar su solicitud.");
        }
        return exception;
    }
    
}
