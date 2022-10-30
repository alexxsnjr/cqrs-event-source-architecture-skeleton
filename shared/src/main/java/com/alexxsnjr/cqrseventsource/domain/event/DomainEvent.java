package com.alexxsnjr.cqrseventsource.domain.event;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class DomainEvent {

    protected String aggregateId;
    private int version = -1;
}
