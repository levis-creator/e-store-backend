package com.micosoft.estoreback.errors;

import lombok.*;
import org.springframework.http.HttpStatus;


public record ApiError(String message, Throwable throwable, HttpStatus httpStatus) {
}
