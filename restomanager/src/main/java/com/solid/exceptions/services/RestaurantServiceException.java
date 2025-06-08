package com.solid.exceptions.services;

import com.solid.enums.EErrorCode;

/**
 * Custom exception for handling restaurant service-level errors.
 * Thrown when a failure occurs during operations in RestaurantServiceImpl.
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public class RestaurantServiceException extends ServiceException {

    /**
     * Constructs a new RestaurantServiceException with the specified error code and
     * message.
     *
     * @param errorCode the specific error code describing the nature of the error
     * @param message   a descriptive message about the exception
     */
    public RestaurantServiceException(EErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    /**
     * Constructs a new RestaurantServiceException with the specified error code,
     * message, and cause.
     *
     * @param errorCode the specific error code describing the nature of the error
     * @param message   a descriptive message about the exception
     * @param cause     the original exception that caused this error
     */
    public RestaurantServiceException(EErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
