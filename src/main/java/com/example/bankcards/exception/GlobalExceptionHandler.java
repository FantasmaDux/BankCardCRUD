package com.example.bankcards.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<String> handleCardNotFoundException(CardNotFoundException e) {
        log.warn(e.getMessage());
        String message = "Card not found";
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(CardRequestNotFoundException.class)
    public ResponseEntity<String> handleCardRequestNotFoundException(CardRequestNotFoundException e) {
        log.warn(e.getMessage());
        String message = "Card request not found";
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }


}
