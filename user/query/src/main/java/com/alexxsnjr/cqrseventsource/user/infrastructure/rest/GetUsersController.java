package com.alexxsnjr.cqrseventsource.user.infrastructure.rest;

import com.alexxsnjr.cqrseventsource.domain.query.QueryBus;
import com.alexxsnjr.cqrseventsource.infrastructure.bus.QueryBusController;
import com.alexxsnjr.cqrseventsource.user.application.GetAllUserQuery;
import com.alexxsnjr.cqrseventsource.user.domain.User;
import com.alexxsnjr.cqrseventsource.user.infrastructure.dto.UserResponse;
import com.alexxsnjr.cqrseventsource.user.infrastructure.mapper.UserMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class GetUsersController extends QueryBusController {

    private UserMapper mapper;

    public GetUsersController(QueryBus queryBus, UserMapper mapper) {
        super(queryBus);
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> queryResult = super.ask(new GetAllUserQuery());
        log.info("ask all user query");
        var response = queryResult
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
