package com.alexxsnjr.cqrseventsource.domain.event;

import com.alexxsnjr.cqrseventsource.domain.Identifier;
import java.util.List;

public interface EventStoreRepository {

    void saveEvents(Identifier aggregateId, Iterable<DomainEvent> events, int version);

    List<DomainEvent> getEvent(Identifier aggregateId);
}
