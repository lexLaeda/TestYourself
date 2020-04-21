package com.test.yourself.exception;

public class TestAlreadyExistException extends RuntimeException {
    public TestAlreadyExistException() {
    }

    public TestAlreadyExistException(String message) {
        super(message);
    }

    public TestAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
