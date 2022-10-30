package com.alexxsnjr.cqrseventsource.user.infrastructure.consumer;

import com.alexxsnjr.cqrseventsource.domain.event.DomainEventConsumer;
import com.alexxsnjr.cqrseventsource.user.UserDeletedEvent;
import com.alexxsnjr.cqrseventsource.user.application.UserDeletedEventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDeletedEventConsumer implements DomainEventConsumer {

    private final UserDeletedEventHandler handler;

    @KafkaListener(topics = "UserDeletedEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload UserDeletedEvent event, Acknowledgment ack) {
        handler.on(event);
        ack.acknowledge();
    }

}
