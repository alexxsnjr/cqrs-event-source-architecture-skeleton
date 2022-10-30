package com.alexxsnjr.cqrseventsource.domain;

import com.alexxsnjr.cqrseventsource.domain.event.DomainEvent;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AggregateRoot {

    private final List<DomainEvent> changes = new ArrayList<>();
    private int version = -1;

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<DomainEvent> getUncommitedChanges() {
        return this.changes;
    }

    public void markChangesAsCommitted() {
        this.changes.clear();
    }

    protected void raiseEvent(DomainEvent event) {
        changes.add(event);
    }

    public abstract Identifier getIdentifier();
}
