package com.alexxsnjr.cqrseventsource.user.infrastructure.config;

import com.alexxsnjr.cqrseventsource.user.application.GetAllUserQueryHandler;
import com.alexxsnjr.cqrseventsource.user.application.UserCreatedEventHandler;
import com.alexxsnjr.cqrseventsource.user.application.UserDeletedEventHandler;
import com.alexxsnjr.cqrseventsource.user.application.UserFinder;
import com.alexxsnjr.cqrseventsource.user.application.UserMailChangedEventHandler;
import com.alexxsnjr.cqrseventsource.user.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringServicesBean {

    @Bean
    public UserFinder userFinder(UserRepository userRepository) {
        return new UserFinder(userRepository);
    }

    @Bean
    public GetAllUserQueryHandler getAllUserQueryHandler(UserFinder userFinder) {
        return new GetAllUserQueryHandler(userFinder);
    }

    @Bean
    public UserCreatedEventHandler userCreatedEventHandler(UserRepository userRepository) {
        return new UserCreatedEventHandler(userRepository);
    }

    @Bean
    public UserDeletedEventHandler userDeletedEventHandler(UserRepository userRepository) {
        return new UserDeletedEventHandler(userRepository);
    }

    @Bean
    public UserMailChangedEventHandler userMailChangedEventHandler(UserRepository userRepository) {
        return new UserMailChangedEventHandler(userRepository);
    }
}
