package com.alexxsnjr.cqrseventsource.user.infrastructure;

import static java.util.Objects.isNull;

import com.alexxsnjr.cqrseventsource.domain.Identifier;
import com.alexxsnjr.cqrseventsource.domain.error.AggregateNotFoundException;
import com.alexxsnjr.cqrseventsource.domain.event.DomainEvent;
import com.alexxsnjr.cqrseventsource.domain.event.EventStoreRepository;
import com.alexxsnjr.cqrseventsource.infrastructure.EventModel;
import com.alexxsnjr.cqrseventsource.user.domain.error.EventVersionNotFoundError;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MongoEventStoreRepository implements EventStoreRepository {

    private final EventStoreSpringRepository eventStoreRepository;
    private final KafkaEventProducer eventProducer;

    @Override
    public void saveEvents(Identifier aggregateId, Iterable<DomainEvent> events,
        int expectedVersion) {
        /*var eventStream = eventStoreRepository.findByAggregateId(aggregateId.value());
        if (expectedVersion != -1
            && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion) {
            throw new EventVersionNotFoundError("event version not found");
        }*/

        var version = expectedVersion;
        for (var event : events) {
            version++;
            event.setVersion(version);
            var eventModel = EventModel.builder()
                .timeStamp(new Date())
                .aggregateId(aggregateId.value())
                .aggregateType(User.class.getTypeName())
                .version(version)
                .eventType(event.getClass().getTypeName())
                .domainEvent(event)
                .build();
            var persistedEvent = eventStoreRepository.save(eventModel);
            if (!persistedEvent.getId().isEmpty()) {
                eventProducer.produce(event.getClass().getSimpleName(), event);
            }
        }

    }

    @Override
    public List<DomainEvent> getEvent(Identifier aggregateId) {
        var eventStream = eventStoreRepository.findByAggregateId(aggregateId.value());
        if (isNull(eventStream) || eventStream.isEmpty()) {
            throw new AggregateNotFoundException("Incorrect User");
        }

        return eventStream.stream().map(EventModel::getDomainEvent).collect(Collectors.toList());
    }
}
