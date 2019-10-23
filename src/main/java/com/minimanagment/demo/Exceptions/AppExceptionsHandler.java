package com.minimanagment.demo.Exceptions;

public class AppExceptionsHandler extends RuntimeException {

    public AppExceptionsHandler(String message) {
        super(message);
    }

    public AppExceptionsHandler(String message, Throwable cause) {
        super(message, cause);
    }
}
