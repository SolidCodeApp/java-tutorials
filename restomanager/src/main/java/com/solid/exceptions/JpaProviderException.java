package com.solid.exceptions;

/**
 * Exception thrown to indicate errors related to the JPA provider or its
 * configuration.
 * Used to signal problems specifically tied to JPA setup or runtime issues
 * outside
 * the standard persistence exceptions.
 * 
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public class JpaProviderException extends RuntimeException {

    /**
     * Constructs a new JpaProviderException with the specified detail message.
     * 
     * @param message the detail message explaining the reason for the exception
     */
    public JpaProviderException(String message) {
        super(message);
    }

    /**
     * Constructs a new JpaProviderException with the specified detail message and
     * cause.
     * 
     * @param messString the detail message explaining the reason for the exception
     * @param exception  the cause of this exception
     */
    public JpaProviderException(String messString, Throwable exception) {
        super(messString, exception);
    }
}
