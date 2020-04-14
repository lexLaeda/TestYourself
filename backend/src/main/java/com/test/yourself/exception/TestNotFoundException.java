package com.test.yourself.exception;

public class TestNotFoundException extends RuntimeException {
    public TestNotFoundException() {
    }

    public TestNotFoundException(String message) {
        super(message);
    }

    public TestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
