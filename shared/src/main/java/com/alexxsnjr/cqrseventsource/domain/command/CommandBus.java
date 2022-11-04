package com.alexxsnjr.cqrseventsource.domain.command;


import com.alexxsnjr.cqrseventsource.domain.error.HandlerNotFoundException;

public interface CommandBus {

    void dispatch(Command command) throws HandlerNotFoundException;

}
