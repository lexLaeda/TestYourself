package com.test.yourself.exception;

public class AnswerValidationException extends RuntimeException {
    public AnswerValidationException() {
    }

    public AnswerValidationException(String message) {
        super(message);
    }

    public AnswerValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
