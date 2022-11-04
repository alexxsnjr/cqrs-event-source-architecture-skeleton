package com.alexxsnjr.cqrseventsource.domain.error;

public final class HandlerNotFoundException extends RuntimeException {

    public static final String MESSAGE = "It was not possible to find a proper handler for the required command";

    public HandlerNotFoundException() {
        super(MESSAGE);
    }

}