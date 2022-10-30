package com.alexxsnjr.cqrseventsource.domain.command;

public interface CommandHandler<T extends Command> {

    void handle(T command);

}
