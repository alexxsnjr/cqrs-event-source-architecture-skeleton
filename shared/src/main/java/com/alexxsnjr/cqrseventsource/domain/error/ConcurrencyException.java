package com.alexxsnjr.cqrseventsource.domain.error;

public class ConcurrencyException extends DomainException {

    public ConcurrencyException(String errorMessage) {
        super(errorMessage);
    }
}
