package com.solid.exceptions;

import com.solid.enums.EErrorCode;
import lombok.Getter;

/**
 * Base class for all custom runtime exceptions in the application.
 * Carries an error code for better classification and handling of errors.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
@Getter
public class AppException extends RuntimeException {

    private final EErrorCode errorCode;

    /**
     * Constructs a new AppException with the specified error code and message.
     *
     * @param errorCode the specific error code representing the error type
     * @param message   a descriptive message explaining the exception
     */
    public AppException(EErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Constructs a new AppException with the specified error code, message, and
     * cause.
     *
     * @param errorCode the specific error code representing the error type
     * @param message   a descriptive message explaining the exception
     * @param cause     the underlying cause of the exception
     */
    public AppException(EErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
