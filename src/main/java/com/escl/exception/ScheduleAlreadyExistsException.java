package com.escl.exception;

public class ScheduleAlreadyExistsException extends RuntimeException {

    public ScheduleAlreadyExistsException(String message) {
        super(message);
    }
}