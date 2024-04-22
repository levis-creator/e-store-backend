package com.micosoft.estoreback.errors;

import org.springframework.http.HttpStatus;


public record ApiError(String message, HttpStatus httpStatus, Throwable throwable) {
    public ApiError(String message, HttpStatus httpStatus) {
        this(message, httpStatus, null);}
}
