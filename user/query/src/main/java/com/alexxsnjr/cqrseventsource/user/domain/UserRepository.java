package com.alexxsnjr.cqrseventsource.user.domain;


import com.alexxsnjr.cqrseventsource.user.UserId;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findUserByEmail(String email);

    List<User> findAllUsers();

    void save(User user);

    Optional<User> findUserById(String aggregateId);

    void delete(UserId id);
}
