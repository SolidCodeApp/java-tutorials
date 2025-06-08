package com.solid.exceptions.managers;

import com.solid.enums.EErrorCode;
import com.solid.exceptions.AppException;

/**
 * Base exception class for all exceptions thrown from the manager layer.
 * Extends {@link AppException} to carry a standardized error code and message.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public class ManagerException extends AppException {

    /**
     * Constructs a new ManagerException with the specified error code and message.
     *
     * @param errorCode the associated error code
     * @param message   the detail message
     */
    public ManagerException(EErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    /**
     * Constructs a new ManagerException with the specified error code, message and
     * cause.
     *
     * @param errorCode the associated error code
     * @param message   the detail message
     * @param cause     the cause of the exception
     */
    public ManagerException(EErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

}
