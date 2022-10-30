package com.alexxsnjr.cqrseventsource.user.application;

import com.alexxsnjr.cqrseventsource.domain.error.AggregateNotFoundException;
import com.alexxsnjr.cqrseventsource.domain.event.DomainEventHandler;
import com.alexxsnjr.cqrseventsource.user.UserDeletedEvent;
import com.alexxsnjr.cqrseventsource.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDeletedEventHandler implements DomainEventHandler<UserDeletedEvent> {

    private final UserRepository repository;

    @Override
    public void on(UserDeletedEvent event) {
        var user =
            repository
                .findUserById(event.getAggregateId())
                .orElseThrow(() -> new AggregateNotFoundException(
                    String.format("user not found %s", event.getAggregateId())));
        repository.delete(user.getId());
    }

}
