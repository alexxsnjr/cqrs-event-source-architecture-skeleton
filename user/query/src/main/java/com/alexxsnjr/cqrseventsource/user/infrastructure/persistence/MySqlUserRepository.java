package com.alexxsnjr.cqrseventsource.user.infrastructure.persistence;

import com.alexxsnjr.cqrseventsource.user.UserId;
import com.alexxsnjr.cqrseventsource.user.domain.User;
import com.alexxsnjr.cqrseventsource.user.domain.UserRepository;
import com.alexxsnjr.cqrseventsource.user.infrastructure.mapper.UserMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MySqlUserRepository implements UserRepository {

    private final UserSpringRepository repository;
    private final UserMapper mapper;

    @Override
    public Optional<User> findUserByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public List<User> findAllUsers() {
        return mapper.toDomain(repository.findAll());
    }

    @Override
    public void save(User user) {
        repository.save(mapper.toEntity(user));
    }

    @Override
    public Optional<User> findUserById(String aggregateId) {
        return repository.findById(aggregateId).map(mapper::toDomain);
    }

    @Override
    public void delete(UserId id) {
        repository.deleteById(id.value());
    }
}
