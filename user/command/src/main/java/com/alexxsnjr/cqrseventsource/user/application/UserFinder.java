package com.alexxsnjr.cqrseventsource.user.application;


import com.alexxsnjr.cqrseventsource.domain.Identifier;
import com.alexxsnjr.cqrseventsource.domain.event.DomainEvent;
import com.alexxsnjr.cqrseventsource.domain.event.EventStoreRepository;
import com.alexxsnjr.cqrseventsource.user.UserActive;
import com.alexxsnjr.cqrseventsource.user.UserCreatedEvent;
import com.alexxsnjr.cqrseventsource.user.UserId;
import com.alexxsnjr.cqrseventsource.user.UserType;
import com.alexxsnjr.cqrseventsource.user.domain.User;
import com.alexxsnjr.cqrseventsource.user.domain.error.EventVersionNotFoundError;
import com.alexxsnjr.cqrseventsource.user.domain.error.UserNotFound;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserFinder {

    private final EventStoreRepository eventStore;

    public User getById(Identifier id) {
        var events = eventStore.getEvent(id);
        if (events != null && !events.isEmpty()) {
            return toDomain(events, id);
        } else {
            throw new UserNotFound("user not found in event store");
        }
    }

    private User toDomain(List<DomainEvent> events, Identifier id) {
        var latestVersion = getLatestVersion(events);
        UserType userType = getType(events);

        var user = User
            .builder()
            .userId(UserId.builder().value(id.value()).build())
            .type(userType)
            .build();
        user.setVersion(latestVersion);
        return user;
    }

    private UserType getType(List<DomainEvent> events) {
        var createdEvent =
            (UserCreatedEvent) events.stream()
                .filter(event -> event.getClass().equals(UserCreatedEvent.class))
                .findFirst().orElseThrow(() -> new UserNotFound("Can't found user typw"));

        return createdEvent.getUserType();
    }

    private Integer getLatestVersion(List<DomainEvent> events) {
        return events.stream()
            .map(DomainEvent::getVersion)
            .max(Comparator.naturalOrder())
            .orElseThrow(() -> new EventVersionNotFoundError("BadVersion"));
    }
}
