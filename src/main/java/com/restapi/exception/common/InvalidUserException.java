package com.restapi.exception.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class InvalidUserException extends RuntimeException {


    /**
     * Constructs an instance of InvalidUserException with the specified error message.
     *
     * @param message The detailed error message explaining the reason for the exception.
     */
    public InvalidUserException(String message) {
        super(message);
    }
}
