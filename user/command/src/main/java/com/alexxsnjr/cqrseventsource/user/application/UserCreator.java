package com.alexxsnjr.cqrseventsource.user.application;

import com.alexxsnjr.cqrseventsource.domain.event.EventStoreRepository;
import com.alexxsnjr.cqrseventsource.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreator {

    private final EventStoreRepository eventStore;

    public void saveEvent(User user) {
        eventStore
            .saveEvents(user.getIdentifier(),
                user.getUncommitedChanges(),
                user.getVersion());
        user.markChangesAsCommitted();
    }
}
