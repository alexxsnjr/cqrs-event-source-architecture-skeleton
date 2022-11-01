package com.alexxsnjr.cqrseventsource.user.infrastructure.rest;

import com.alexxsnjr.cqrseventsource.domain.command.CommandBus;
import com.alexxsnjr.cqrseventsource.infrastructure.bus.CommandBusController;
import com.alexxsnjr.cqrseventsource.user.application.DeleteUserCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class DeleteUserlController extends CommandBusController {

    public DeleteUserlController(CommandBus commandBus) {
        super(commandBus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") String id) {
        super.dispatch(DeleteUserCommand.builder().id(id).build());
        log.info("dispatch delete user command");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
