package com.test.yourself.exception;

public class AnswerNotFoundException extends RuntimeException {
    public AnswerNotFoundException() {
    }

    public AnswerNotFoundException(String message) {
        super(message);
    }

    public AnswerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
