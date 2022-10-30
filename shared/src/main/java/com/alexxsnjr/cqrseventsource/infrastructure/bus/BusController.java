package com.alexxsnjr.cqrseventsource.infrastructure.bus;

import com.alexxsnjr.cqrseventsource.domain.command.Command;
import com.alexxsnjr.cqrseventsource.domain.command.CommandBus;
import com.alexxsnjr.cqrseventsource.domain.error.CommandHandlerNotFoundException;
import com.alexxsnjr.cqrseventsource.domain.query.Query;
import com.alexxsnjr.cqrseventsource.domain.error.QueryHandlerExecutionException;
import com.alexxsnjr.cqrseventsource.domain.query.Result;
import com.alexxsnjr.cqrseventsource.domain.query.QueryBus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BusController {

    private final QueryBus queryBus;
    private final CommandBus commandBus;

    protected void dispatch(Command command) throws CommandHandlerNotFoundException {
        commandBus.dispatch(command);
    }

    protected <R extends Result> R ask(Query query) throws QueryHandlerExecutionException {
        return queryBus.ask(query);
    }

}
