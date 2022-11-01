package com.alexxsnjr.cqrseventsource.user.infrastructure;

import com.alexxsnjr.cqrseventsource.infrastructure.EventModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface EventStoreSpringRepository extends MongoRepository<EventModel, String> {
    List<EventModel> findByAggregateIdentifier(String aggregateIdentifier);
}

