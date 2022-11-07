package com.alexxsnjr.cqrseventsource.user.application;

import com.alexxsnjr.cqrseventsource.domain.error.AggregateNotFoundException;
import com.alexxsnjr.cqrseventsource.domain.event.DomainEventHandler;
import com.alexxsnjr.cqrseventsource.user.UserMailChangedEvent;
import com.alexxsnjr.cqrseventsource.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserMailChangedEventHandler implements DomainEventHandler<UserMailChangedEvent> {

    private final UserRepository repository;

    @Override
    public void on(UserMailChangedEvent event) {
        var user =
            repository
                .findUserById(event.getAggregateId())
                .orElseThrow(() -> new AggregateNotFoundException(
                    String.format("user not found %s", event.getAggregateId())));
        user.changheMail(event.getEmail());
        repository.save(user);
    }

}
