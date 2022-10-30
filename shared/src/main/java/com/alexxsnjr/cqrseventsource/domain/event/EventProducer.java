package com.alexxsnjr.cqrseventsource.domain.event;

import com.alexxsnjr.cqrseventsource.domain.event.DomainEvent;

public interface EventProducer {

    void produce(String topic, DomainEvent event);
}
