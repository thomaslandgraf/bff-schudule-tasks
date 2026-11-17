package com.landgraf.bffscheduletasks.infrastructure.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
