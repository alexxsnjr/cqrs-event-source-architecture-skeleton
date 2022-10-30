package com.alexxsnjr.cqrseventsource.user.application;

import com.alexxsnjr.cqrseventsource.domain.command.Command;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChangeUserMailCommand implements Command {

    private String id;
    private String email;

}
