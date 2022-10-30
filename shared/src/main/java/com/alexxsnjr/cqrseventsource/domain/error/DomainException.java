package com.alexxsnjr.cqrseventsource.domain.error;

public abstract class DomainException extends RuntimeException {
    private final String errorMessage;

    public DomainException(String errorMessage) {
        super(errorMessage);

        this.errorMessage = errorMessage;
    }

    public String errorMessage() {
        return errorMessage;
    }
}
