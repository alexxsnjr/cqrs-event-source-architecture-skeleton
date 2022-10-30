package com.alexxsnjr.cqrseventsource.user.infrastructure.consumer;

import com.alexxsnjr.cqrseventsource.domain.event.DomainEventConsumer;
import com.alexxsnjr.cqrseventsource.user.UserCreatedEvent;
import com.alexxsnjr.cqrseventsource.user.application.UserCreatedEventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreatedEventConsumer implements DomainEventConsumer {

    private final UserCreatedEventHandler handler;

    @KafkaListener(topics = "UserCreatedEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload UserCreatedEvent event, Acknowledgment ack) {
        handler.on(event);
        ack.acknowledge();
    }

}
