package com.alexxsnjr.cqrseventsource.user.infrastructure.consumer;

import com.alexxsnjr.cqrseventsource.domain.event.DomainEventConsumer;
import com.alexxsnjr.cqrseventsource.user.UserMailChangedEvent;
import com.alexxsnjr.cqrseventsource.user.application.UserMailChangedEventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMailChangedEventConsumer implements DomainEventConsumer {

    private final UserMailChangedEventHandler handler;

    @KafkaListener(topics = "UserMailChangedEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload UserMailChangedEvent event, Acknowledgment ack) {
        handler.on(event);
        ack.acknowledge();
    }

}
