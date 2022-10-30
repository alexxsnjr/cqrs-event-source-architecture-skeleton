package com.alexxsnjr.cqrseventsource.user.infrastructure;

import com.alexxsnjr.cqrseventsource.domain.event.DomainEvent;
import com.alexxsnjr.cqrseventsource.domain.event.EventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaEventProducer implements EventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void produce(String topic, DomainEvent event) {

        this.kafkaTemplate.send(topic, event);

    }
}
