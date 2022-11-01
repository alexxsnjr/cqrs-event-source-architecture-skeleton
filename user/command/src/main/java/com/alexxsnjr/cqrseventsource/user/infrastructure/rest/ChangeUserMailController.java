package com.alexxsnjr.cqrseventsource.user.infrastructure.rest;

import com.alexxsnjr.cqrseventsource.domain.command.CommandBus;
import com.alexxsnjr.cqrseventsource.domain.query.Result;
import com.alexxsnjr.cqrseventsource.infrastructure.bus.CommandBusController;
import com.alexxsnjr.cqrseventsource.user.application.ChangeUserMailCommand;
import com.alexxsnjr.cqrseventsource.user.infrastructure.mapper.UserMapperResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class ChangeUserMailController extends CommandBusController {

    public ChangeUserMailController(CommandBus commandBus) {
        super(commandBus);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Result> createUser(@PathVariable(value = "id") String id,
        @RequestBody String newMailRequest) {
        super.dispatch(ChangeUserMailCommand.builder().id(id).email(newMailRequest).build());
        log.info("dispatch change user mail command");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
