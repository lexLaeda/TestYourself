package com.test.yourself.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SubjectServiceValidationException extends RuntimeException{

    public SubjectServiceValidationException() {
    }

    public SubjectServiceValidationException(String message) {
        super(message);
    }

    public SubjectServiceValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
