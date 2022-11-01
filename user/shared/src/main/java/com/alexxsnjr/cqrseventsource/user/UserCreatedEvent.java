package com.alexxsnjr.cqrseventsource.user;

import com.alexxsnjr.cqrseventsource.domain.event.DomainEvent;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedEvent extends DomainEvent {

    private String name;
    private String surname;
    private String email;
    private String phone;
    private boolean active;
    private UserType userType;
    private Date createDate;

}
