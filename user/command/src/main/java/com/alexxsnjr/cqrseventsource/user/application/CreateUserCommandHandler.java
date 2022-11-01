package com.alexxsnjr.cqrseventsource.user.application;

import com.alexxsnjr.cqrseventsource.domain.command.CommandHandler;
import com.alexxsnjr.cqrseventsource.user.UserActive;
import com.alexxsnjr.cqrseventsource.user.UserContact;
import com.alexxsnjr.cqrseventsource.user.UserId;
import com.alexxsnjr.cqrseventsource.user.UserName;
import com.alexxsnjr.cqrseventsource.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserCommandHandler implements CommandHandler<CreateNewUserCommand> {

    private final UserCreator creator;

    @Override
    public void handle(CreateNewUserCommand command) {
        var user = buildUser(command);
        user.createNewUser();
        creator.saveEvent(user);
    }

    private User buildUser(CreateNewUserCommand command) {
        return User.builder()
            .userId(UserId
                .builder()
                .value(command.getId())
                .build())
            .name(UserName
                .builder()
                .name(command.getName())
                .surname(command.getSurname())
                .build())
            .contact(UserContact
                .builder()
                .email(command.getEmail())
                .phone(command.getPhone())
                .build())
            .active(UserActive.builder().active(true).build())
            .type(command.getUserType())
            .build();
    }
}
