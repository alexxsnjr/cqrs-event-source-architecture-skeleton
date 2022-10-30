package com.alexxsnjr.cqrseventsource.user.domain.error;

import com.alexxsnjr.cqrseventsource.domain.error.DomainException;

public class UserNotDeleteable extends DomainException {

    public UserNotDeleteable(String errorMessage) {
        super(errorMessage);
    }
}
