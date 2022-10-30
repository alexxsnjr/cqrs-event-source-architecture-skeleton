package com.alexxsnjr.cqrseventsource.user.application;


import com.alexxsnjr.cqrseventsource.domain.command.Command;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeleteUserCommand implements Command {

    private String id;

}
