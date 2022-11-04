package com.alexxsnjr.cqrseventsource.infrastructure.bus;

import com.alexxsnjr.cqrseventsource.domain.command.Command;
import com.alexxsnjr.cqrseventsource.domain.command.CommandBus;
import com.alexxsnjr.cqrseventsource.domain.error.HandlerNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CommandBusController {

    private final CommandBus commandBus;

    protected void dispatch(Command command) throws HandlerNotFoundException {
        commandBus.dispatch(command);
    }

}
