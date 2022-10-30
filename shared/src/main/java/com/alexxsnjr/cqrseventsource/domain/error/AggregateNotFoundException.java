package com.alexxsnjr.cqrseventsource.domain.error;

public class AggregateNotFoundException extends DomainException {

    public AggregateNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
