package com.alexxsnjr.cqrseventsource.user.infrastructure.mapper;

import com.alexxsnjr.cqrseventsource.user.UserActive;
import com.alexxsnjr.cqrseventsource.user.UserContact;
import com.alexxsnjr.cqrseventsource.user.UserId;
import com.alexxsnjr.cqrseventsource.user.UserName;
import com.alexxsnjr.cqrseventsource.user.domain.User;
import com.alexxsnjr.cqrseventsource.user.infrastructure.dto.UserResponse;
import com.alexxsnjr.cqrseventsource.user.infrastructure.persistence.UserEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User toDomain(UserEntity userEntity) {
        return User.builder()
            .id(UserId
                .builder()
                .value(userEntity.getId())
                .build())
            .active(UserActive
                .builder()
                .active(userEntity.isActive())
                .build())
            .name(UserName
                .builder()
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .build())
            .contact(UserContact
                .builder()
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .build())
            .type(userEntity.getUserType())
            .build();
    }

    public List<User> toDomain(Iterable<UserEntity> userEntities) {
        List<User> users = new ArrayList<>();
        Iterator it = userEntities.iterator();

        while (it.hasNext()) {
            users.add(toDomain((UserEntity) it.next()));
        }
        return users;
    }

    public UserEntity toEntity(User user) {
        return UserEntity
            .builder()
            .id(user.getId().value())
            .name(user.getName().getName())
            .surname(user.getName().getSurname())
            .email(user.getContact().getEmail())
            .phone(user.getContact().getPhone())
            .active(user.getActive().isActive())
            .userType(user.getType())
            .build();
    }

    public UserResponse toDto(User user) {
        return UserResponse
            .builder()
            .userId(user.getId().value())
            .email(user.getContact().getEmail())
            .phone(user.getContact().getPhone())
            .name(user.getName().getName())
            .surname(user.getName().getSurname())
            .type(user.getType())
            .build();
    }
}
