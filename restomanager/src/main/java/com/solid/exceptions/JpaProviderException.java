package com.solid.exceptions;

public class JpaProviderException extends RuntimeException {

    public JpaProviderException(String message) {
        super(message);

    }

    public JpaProviderException(String messString, Throwable exception) {
        super(messString, exception);
    }

}
