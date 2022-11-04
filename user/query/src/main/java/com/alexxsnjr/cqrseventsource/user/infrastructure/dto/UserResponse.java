package com.alexxsnjr.cqrseventsource.user.infrastructure.dto;

import com.alexxsnjr.cqrseventsource.user.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {

    private String userId;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private UserType type;
}
