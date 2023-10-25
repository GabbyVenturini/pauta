package com.schedule.vote.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class HttpErrorExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> badRequest(BadRequestException exception){
        return buildErrorResponse(HttpStatus.BAD_REQUEST,exception.getMessage());
    }
    private ResponseEntity<ApiError> buildErrorResponse(HttpStatus status,String message){
        var error = new ApiError(status.value(),message, Instant.now().toEpochMilli());
        return ResponseEntity.status(status).body(error);
    }
}
