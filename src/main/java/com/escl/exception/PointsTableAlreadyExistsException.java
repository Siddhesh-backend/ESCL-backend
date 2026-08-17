package com.escl.exception;

public class PointsTableAlreadyExistsException extends RuntimeException {

    public PointsTableAlreadyExistsException(String message) {
        super(message);
    }
}