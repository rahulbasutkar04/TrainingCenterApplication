package com.trainieight.trainingcenterapplication.TrainingCenterApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> response = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((errors) -> {
            String fieldName = ((FieldError) errors).getField();
            String message = errors.getDefaultMessage();
            response.put(fieldName, message);
        });

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CenterAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleCenterAlreadyExistsException(CenterAlreadyExistsException ex) {
        return ex.getMessage();
    }

}
