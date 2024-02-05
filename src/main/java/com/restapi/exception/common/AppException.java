package com.restapi.exception.common;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

    private HttpStatus status;
    private String message;

    // Constructor to initialize the exception with status and message
    public AppException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    // Constructor with an additional parameter for the exception message
    public AppException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    // Getter method for retrieving the HttpStatus
    public HttpStatus getStatus() {
        return status;
    }

    // Override getMessage method to provide the exception message
    @Override
    public String getMessage() {
        return message;
    }
}
