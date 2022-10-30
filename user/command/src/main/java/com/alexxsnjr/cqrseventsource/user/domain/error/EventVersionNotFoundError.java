package com.alexxsnjr.cqrseventsource.user.domain.error;

import com.alexxsnjr.cqrseventsource.domain.error.DomainException;

public class EventVersionNotFoundError extends DomainException {

    public EventVersionNotFoundError(String errorMessage) {
        super(errorMessage);
    }
}
