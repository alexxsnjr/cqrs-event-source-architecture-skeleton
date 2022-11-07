package com.alexxsnjr.cqrseventsource.user.application;

import com.alexxsnjr.cqrseventsource.domain.command.CommandHandler;
import com.alexxsnjr.cqrseventsource.user.UserId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChangeUserMailCommandHandler implements CommandHandler<ChangeUserMailCommand> {

    private final UserEventCreator creator;
    private final UserEventFinder finder;

    @Override
    public void handle(ChangeUserMailCommand command) {
        UserId userId = UserId.builder().value(command.getId()).build();
        var user = finder.getById(userId);
        user.changeUserMail(command.getEmail());
        creator.saveEvent(user);
    }
}
