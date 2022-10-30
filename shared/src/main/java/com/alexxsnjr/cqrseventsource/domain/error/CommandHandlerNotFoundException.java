package com.alexxsnjr.cqrseventsource.domain.error;

public final class CommandHandlerNotFoundException extends RuntimeException {

    public static final String MESSAGE = "It was not possible to find a proper handler for the required command";

    public CommandHandlerNotFoundException() {
        super(MESSAGE);
    }

}