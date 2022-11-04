package com.alexxsnjr.cqrseventsource.user.application;

import com.alexxsnjr.cqrseventsource.user.domain.User;
import com.alexxsnjr.cqrseventsource.user.domain.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFinder {

    private final UserRepository repository;

    public List<User> getAllUser() {
        return repository.findAllUsers();
    }

}
