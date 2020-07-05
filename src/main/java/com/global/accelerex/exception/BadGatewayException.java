package com.global.accelerex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class BadGatewayException extends Exception{

    private static final long serialVersionUID = 1L;

    public BadGatewayException(String message){
        super(message);
    }
}