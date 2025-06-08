package com.solid.exceptions;

import com.solid.enums.EErrorCode;

/**
 * Exception class for DAO (Data Access Object) layer errors.
 * Wraps low-level data persistence issues and carries a corresponding error
 * code.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public class DaoException extends AppException {

    /**
     * Constructs a new DaoException with the specified error code and message.
     *
     * @param errorCode the specific error code representing the data access error
     * @param message   a descriptive message explaining the exception
     */
    public DaoException(EErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    /**
     * Constructs a new DaoException with the specified error code, message, and
     * cause.
     *
     * @param errorCode the specific error code representing the data access error
     * @param message   a descriptive message explaining the exception
     * @param throwable the underlying cause of the exception
     */
    public DaoException(EErrorCode errorCode, String message, Throwable throwable) {
        super(errorCode, message, throwable);
    }
}
