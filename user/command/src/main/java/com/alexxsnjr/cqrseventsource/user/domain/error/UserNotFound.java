package com.alexxsnjr.cqrseventsource.user.domain.error;

import com.alexxsnjr.cqrseventsource.domain.error.DomainException;

public class UserNotFound extends DomainException {

    public UserNotFound(String errorMessage) {
        super(errorMessage);
    }
}
