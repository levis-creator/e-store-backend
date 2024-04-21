package com.micosoft.estoreback.errors.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExist extends RuntimeException{

    public AlreadyExist(String message) {
        super(message);
    }

    public AlreadyExist(String message, Throwable cause) {
        super(message, cause);
    }
}
