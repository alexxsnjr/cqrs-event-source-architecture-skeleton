package com.alexxsnjr.cqrseventsource.user.infrastructure.rest;

import static java.util.Objects.isNull;

import com.alexxsnjr.cqrseventsource.domain.command.CommandBus;
import com.alexxsnjr.cqrseventsource.infrastructure.bus.CommandBusController;
import com.alexxsnjr.cqrseventsource.user.infrastructure.dto.CreateUserRequest;
import com.alexxsnjr.cqrseventsource.user.infrastructure.dto.CreateUserResponse;
import com.alexxsnjr.cqrseventsource.user.infrastructure.mapper.UserMapperResource;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class CreateUserController extends CommandBusController {

    private UserMapperResource mapper;

    public CreateUserController(CommandBus commandBus, UserMapperResource mapper) {
        super(commandBus);
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
        if (isNull(request.getId())) {
            var id = UUID.randomUUID().toString();
            request.setId(id);
        }
        super.dispatch(mapper.toCommand(request));
        log.info("dispatch create user command");
        return new ResponseEntity<>(new CreateUserResponse(request.getId()),HttpStatus.CREATED);
    }

}
