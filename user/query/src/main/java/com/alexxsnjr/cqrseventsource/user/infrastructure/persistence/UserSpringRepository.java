package com.alexxsnjr.cqrseventsource.user.infrastructure.persistence;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSpringRepository extends CrudRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);

}
