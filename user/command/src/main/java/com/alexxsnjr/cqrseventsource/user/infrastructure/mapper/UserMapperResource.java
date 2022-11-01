package com.alexxsnjr.cqrseventsource.user.infrastructure.mapper;

import com.alexxsnjr.cqrseventsource.user.application.CreateNewUserCommand;
import com.alexxsnjr.cqrseventsource.user.infrastructure.dto.CreateUserRequest;
import org.springframework.stereotype.Service;

@Service
public class UserMapperResource {

    public CreateNewUserCommand toCommand(CreateUserRequest request) {
        return CreateNewUserCommand
            .builder()
            .id(request.getId())
            .email(request.getEmail())
            .name(request.getName())
            .surname(request.getSurnameIfExist())
            .phone(request.getPhoneIfExist())
            .userType(request.getUserType())
            .build();
    }
}
