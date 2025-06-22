package com.workintech.fswebs18challengemaven.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {CardException.class})
    public ResponseEntity<CardErrorResponse> handleCardException(CardException ex) {
        log.error("Card exception: {}", ex.getMessage());
        CardErrorResponse errorResponse = new CardErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<CardErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        log.error("Validation failed: {}", ex.getMessage());
        CardErrorResponse errorResponse = new CardErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<CardErrorResponse> handleRuntime(RuntimeException ex) {
        log.error("Application error: {}", ex.getMessage());
        CardErrorResponse errorResponse = new CardErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}