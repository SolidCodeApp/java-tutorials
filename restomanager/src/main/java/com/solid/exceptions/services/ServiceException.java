package com.solid.exceptions.services;

import com.solid.enums.EErrorCode;
import com.solid.exceptions.AppException;

/**
 * Base exception class for all service-level exceptions in the application.
 * Extends AppException to provide more specific context for service layer
 * errors.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public class ServiceException extends AppException {

    /**
     * Constructs a new ServiceException with the specified error code and message.
     *
     * @param errorCode the specific error code representing the error type
     * @param message   a descriptive message explaining the exception
     */
    public ServiceException(EErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    /**
     * Constructs a new ServiceException with the specified error code, message, and
     * cause.
     *
     * @param errorCode the specific error code representing the error type
     * @param message   a descriptive message explaining the exception
     * @param cause     the underlying cause of the exception
     */
    public ServiceException(EErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
