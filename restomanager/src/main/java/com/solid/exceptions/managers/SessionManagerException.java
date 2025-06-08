package com.solid.exceptions.managers;

import com.solid.enums.EErrorCode;

/**
 * Custom exception thrown for session management-related errors.
 * Wraps low-level exceptions with a business-friendly context.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public class SessionManagerException extends ManagerException {

    /**
     * Constructs a new SessionManagerException with the specified error code and
     * message.
     *
     * @param errorCode the specific error code representing the failure
     * @param message   a human-readable message describing the error
     */
    public SessionManagerException(EErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    /**
     * Constructs a new SessionManagerException with the specified error code,
     * message, and cause.
     *
     * @param errorCode the specific error code representing the failure
     * @param message   a human-readable message describing the error
     * @param cause     the original exception that triggered this one
     */
    public SessionManagerException(EErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
