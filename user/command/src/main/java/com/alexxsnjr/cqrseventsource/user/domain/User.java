package com.alexxsnjr.cqrseventsource.user.domain;

import com.alexxsnjr.cqrseventsource.domain.AggregateRoot;
import com.alexxsnjr.cqrseventsource.domain.Identifier;
import com.alexxsnjr.cqrseventsource.user.UserActive;
import com.alexxsnjr.cqrseventsource.user.UserContact;
import com.alexxsnjr.cqrseventsource.user.UserCreatedEvent;
import com.alexxsnjr.cqrseventsource.user.UserDeletedEvent;
import com.alexxsnjr.cqrseventsource.user.UserId;
import com.alexxsnjr.cqrseventsource.user.UserMailChangedEvent;
import com.alexxsnjr.cqrseventsource.user.UserName;
import com.alexxsnjr.cqrseventsource.user.UserType;
import com.alexxsnjr.cqrseventsource.user.domain.error.InmutableEmail;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User extends AggregateRoot {

    private UserId userId;
    private UserContact contact;
    private UserName name;
    private UserActive active;
    private UserType type;

    public void createNewUser() {
        var event =
            UserCreatedEvent.builder()
                .createDate(new Date())
                .userType(type)
                .name(name.getName())
                .surname(name.getSurname())
                .phone(contact.getPhone())
                .email(contact.getEmail())
                .active(active.isActive())
                .aggregateId(userId.value())
                .build();

        raiseEvent(event);
    }


    public void changeUserMail(String email) {
        if (type.equals(UserType.CUSTOMER)) {
            throw new InmutableEmail("Email can not change if is customer type");
        }

        raiseEvent(UserMailChangedEvent.builder()
            .email(email)
            .aggregateId(this.userId.value())
            .build());
    }

    public void deleteUser() {
        raiseEvent(UserDeletedEvent.builder()
            .aggregateId(this.userId.value())
            .build());
    }

    @Override
    public Identifier getIdentifier() {
        return userId;
    }

}
