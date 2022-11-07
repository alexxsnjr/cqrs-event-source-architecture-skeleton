package com.alexxsnjr.cqrseventsource.user.application;

import com.alexxsnjr.cqrseventsource.domain.event.DomainEventHandler;
import com.alexxsnjr.cqrseventsource.user.UserActive;
import com.alexxsnjr.cqrseventsource.user.UserContact;
import com.alexxsnjr.cqrseventsource.user.UserCreatedEvent;
import com.alexxsnjr.cqrseventsource.user.UserId;
import com.alexxsnjr.cqrseventsource.user.UserName;
import com.alexxsnjr.cqrseventsource.user.domain.User;
import com.alexxsnjr.cqrseventsource.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserCreatedEventHandler implements DomainEventHandler<UserCreatedEvent> {

    private final UserRepository repository;

    @Override
    public void on(UserCreatedEvent event) {
        var user = toDomain(event);
        repository.save(user);
    }

    private User toDomain(UserCreatedEvent event) {
        return User.builder()
            .id(UserId
                .builder()
                .value(event.getAggregateId())
                .build())
            .active(UserActive
                .builder()
                .active(event.isActive())
                .build())
            .name(UserName
                .builder()
                .name(event.getName())
                .surname(event.getSurname())
                .build())
            .contact(UserContact
                .builder()
                .email(event.getEmail())
                .phone(event.getPhone())
                .build())
            .type(event.getUserType())
            .build();
    }
}
