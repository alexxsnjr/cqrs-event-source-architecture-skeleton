package com.alexxsnjr.cqrseventsource.user.application;


import com.alexxsnjr.cqrseventsource.domain.Identifier;
import com.alexxsnjr.cqrseventsource.domain.event.DomainEvent;
import com.alexxsnjr.cqrseventsource.domain.event.EventStoreRepository;
import com.alexxsnjr.cqrseventsource.user.domain.User;
import com.alexxsnjr.cqrseventsource.user.domain.error.EventVersionNotFoundError;
import java.util.Comparator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserFinder {

    private final EventStoreRepository eventStore;

    public User getById(Identifier id) {
        var user = User.builder().build();
        var events = eventStore.getEvent(id);
        if (events != null && !events.isEmpty()) {
            var latestVersion = events.stream().map(DomainEvent::getVersion)
                .max(Comparator.naturalOrder());
            user.setVersion(
                latestVersion.orElseThrow(() -> new EventVersionNotFoundError("BadVersion")));
        }

        return user;
    }
}
