package com.alexxsnjr.cqrseventsource.user.domain.error;

import com.alexxsnjr.cqrseventsource.domain.error.DomainException;

public class InmutableEmail extends DomainException {

    public InmutableEmail(String errorMessage) {
        super(errorMessage);
    }
}
