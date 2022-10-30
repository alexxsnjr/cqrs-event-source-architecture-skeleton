package com.alexxsnjr.cqrseventsource.domain.command;


import com.alexxsnjr.cqrseventsource.domain.error.CommandHandlerNotFoundException;

public interface CommandBus {

    void dispatch(Command command) throws CommandHandlerNotFoundException;

}
