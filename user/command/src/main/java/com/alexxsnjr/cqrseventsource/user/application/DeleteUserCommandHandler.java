package com.alexxsnjr.cqrseventsource.user.application;

import com.alexxsnjr.cqrseventsource.domain.command.CommandHandler;
import com.alexxsnjr.cqrseventsource.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserCommandHandler implements CommandHandler<DeleteUserCommand> {

    private final UserCreator creator;
    private final UserFinder finder;

    @Override
    public void handle(DeleteUserCommand command) {
        UserId userId = UserId.builder().value(command.getId()).build();
        var user = finder.getById(userId);
        user.deleteUser();
        creator.saveEvent(user);
    }
}
