package com.alexxsnjr.cqrseventsource.user.application;

import com.alexxsnjr.cqrseventsource.domain.command.Command;
import com.alexxsnjr.cqrseventsource.user.UserType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateNewUserCommand implements Command {

    private String id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private UserType userType;

}
