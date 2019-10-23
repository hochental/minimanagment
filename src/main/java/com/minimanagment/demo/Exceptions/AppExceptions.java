package com.minimanagment.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class AppExceptions {
    @ExceptionHandler
    public ResponseEntity<Object> handleApiRequestException(AppExceptionsHandler e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        Exceptions exception= new Exceptions(e.getMessage(), e, badRequest, ZonedDateTime.now());
        return new ResponseEntity<>(exception, badRequest);
    }



}
