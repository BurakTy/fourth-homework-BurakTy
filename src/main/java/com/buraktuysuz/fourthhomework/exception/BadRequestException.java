package com.buraktuysuz.fourthhomework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.webjars.NotFoundException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends NotFoundException {
    public BadRequestException(String message){
        super(message);
    }
}
