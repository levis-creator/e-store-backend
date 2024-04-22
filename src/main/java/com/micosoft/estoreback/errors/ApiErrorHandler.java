package com.micosoft.estoreback.errors;

import com.micosoft.estoreback.errors.exceptions.AlreadyExist;
import com.micosoft.estoreback.errors.exceptions.BadRequest;
import com.micosoft.estoreback.errors.exceptions.NotFound;
import com.micosoft.estoreback.errors.exceptions.ServerError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiErrorHandler {
    @ExceptionHandler(NotFound.class)
    public ResponseEntity<?> handleNotFoundException(NotFound exception) {
        ApiError notFoundException = new ApiError(exception.getMessage(),  HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(notFoundException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExist.class)
    public ResponseEntity<?> handleAlreadyExistException(NotFound exception) {
        ApiError alreadyExist = new ApiError(exception.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(alreadyExist, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<?> handlwBadRequestException(BadRequest exception){
        ApiError badRequest = new ApiError(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ServerError.class)
    public ResponseEntity<?> handleInternalException(ServerError exception){
        ApiError serverError= new ApiError(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, exception.getCause());
        return new ResponseEntity<>(serverError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
