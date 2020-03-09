package com.dinosatrovic.synonymssearch.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ErrorMappingsConfig {

    @ExceptionHandler
    public ResponseEntity<Void> handleConstraintViolationException(ConstraintViolationException exception) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("errors", exception.getMessage());
        return new ResponseEntity<Void>(httpHeaders, HttpStatus.BAD_REQUEST);
    }
}