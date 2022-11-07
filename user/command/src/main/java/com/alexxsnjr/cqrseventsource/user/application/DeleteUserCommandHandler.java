package com.alexxsnjr.cqrseventsource.user.application;

import com.alexxsnjr.cqrseventsource.domain.command.CommandHandler;
import com.alexxsnjr.cqrseventsource.user.UserId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteUserCommandHandler implements CommandHandler<DeleteUserCommand> {

    private final UserEventCreator creator;
    private final UserEventFinder finder;

    @Override
    public void handle(DeleteUserCommand command) {
        UserId userId = UserId.builder().value(command.getId()).build();
        var user = finder.getById(userId);
        user.deleteUser();
        creator.saveEvent(user);
    }
}
