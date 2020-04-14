package com.test.yourself.exception;

public class SubjectNotFoundException extends RuntimeException{


    public SubjectNotFoundException() {
    }

    public SubjectNotFoundException(String message) {
        super(message);
    }

    public SubjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
