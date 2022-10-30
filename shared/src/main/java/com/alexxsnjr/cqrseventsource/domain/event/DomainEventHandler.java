package com.alexxsnjr.cqrseventsource.domain.event;

public interface DomainEventHandler<T extends DomainEvent> {

    void on(T event);

}
