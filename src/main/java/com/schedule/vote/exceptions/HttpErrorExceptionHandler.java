package com.schedule.vote.exceptions;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.time.LocalDateTime;

@ControllerAdvice
public class HttpErrorExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> badRequest(BadRequestException exception) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiError> forbidden(ForbiddenException exception) {
        return buildErrorResponse(HttpStatus.FORBIDDEN, exception.getMessage());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ApiError> notFound(ObjectNotFoundException exception) {
        return buildErrorResponse(HttpStatus.NOT_FOUND,"Nao foi possivel encontrar " + exception.getEntityName());
    }

    private ResponseEntity<ApiError> buildErrorResponse(HttpStatus status, String message) {
        var error = new ApiError(status.value(), message, LocalDateTime.now());
        return ResponseEntity.status(status).body(error);
    }


}
