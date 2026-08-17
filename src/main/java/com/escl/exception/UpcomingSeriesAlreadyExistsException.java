package com.escl.exception;

public class UpcomingSeriesAlreadyExistsException extends RuntimeException {

    public UpcomingSeriesAlreadyExistsException(String message) {
        super(message);
    }
}