package com.solid.exceptions;

public class NonUniqueFieldException extends RuntimeException {
    public NonUniqueFieldException(String message) {
        super(message);
    }

    public NonUniqueFieldException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
