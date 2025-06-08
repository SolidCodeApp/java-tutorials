package com.solid.exceptions;

import com.solid.enums.EErrorCode;

/**
 * Exception class used for errors occurring in helper or utility classes.
 * Typically wraps unexpected logic or transformation failures in static utility
 * layers.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public class HelperException extends AppException {

    /**
     * Constructs a new HelperException with the specified error code and message.
     *
     * @param errorCode the specific error code indicating the type of helper error
     * @param message   a human-readable description of the error
     */
    public HelperException(EErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    /**
     * Constructs a new HelperException with the specified error code, message, and
     * cause.
     *
     * @param errorCode the specific error code indicating the type of helper error
     * @param message   a human-readable description of the error
     * @param cause     the underlying cause of the exception
     */
    public HelperException(EErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
