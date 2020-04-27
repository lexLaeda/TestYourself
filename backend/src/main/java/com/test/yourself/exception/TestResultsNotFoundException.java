package com.test.yourself.exception;

public class TestResultsNotFoundException extends RuntimeException{
    public TestResultsNotFoundException() {
    }

    public TestResultsNotFoundException(String message) {
        super(message);
    }

    public TestResultsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
