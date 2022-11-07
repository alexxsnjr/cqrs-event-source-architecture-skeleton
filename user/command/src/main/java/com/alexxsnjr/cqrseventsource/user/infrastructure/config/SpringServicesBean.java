package com.alexxsnjr.cqrseventsource.user.infrastructure.config;

import com.alexxsnjr.cqrseventsource.domain.event.EventStoreRepository;
import com.alexxsnjr.cqrseventsource.user.application.ChangeUserMailCommandHandler;
import com.alexxsnjr.cqrseventsource.user.application.CreateUserCommandHandler;
import com.alexxsnjr.cqrseventsource.user.application.DeleteUserCommandHandler;
import com.alexxsnjr.cqrseventsource.user.application.UserEventCreator;
import com.alexxsnjr.cqrseventsource.user.application.UserEventFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringServicesBean {

    @Bean
    public UserEventFinder userEventFinder(EventStoreRepository eventStoreRepository) {
        return new UserEventFinder(eventStoreRepository);
    }

    @Bean
    public UserEventCreator userEventCreator(EventStoreRepository eventStoreRepository) {
        return new UserEventCreator(eventStoreRepository);
    }

    @Bean
    public ChangeUserMailCommandHandler changeUserMailCommandHandler(
        UserEventCreator userEventCreator, UserEventFinder userEventFinder) {
        return new ChangeUserMailCommandHandler(userEventCreator, userEventFinder);
    }

    @Bean
    public CreateUserCommandHandler createUserCommandHandler(UserEventCreator userEventCreator) {
        return new CreateUserCommandHandler(userEventCreator);
    }

    @Bean
    public DeleteUserCommandHandler deleteUserCommandHandler(UserEventCreator userEventCreator,
        UserEventFinder userEventFinder) {
        return new DeleteUserCommandHandler(userEventCreator, userEventFinder);
    }
}
