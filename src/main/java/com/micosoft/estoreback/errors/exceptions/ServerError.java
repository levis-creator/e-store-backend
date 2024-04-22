package com.micosoft.estoreback.errors.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerError extends RuntimeException {
    public ServerError(String message, Throwable cause) {
        super(message, cause);
    }
    public ServerError(String message) {
        super(message);
    }
}
