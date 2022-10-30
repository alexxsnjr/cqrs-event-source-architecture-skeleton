package com.alexxsnjr.cqrseventsource.user;

import com.alexxsnjr.cqrseventsource.domain.event.DomainEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class UserMailChangedEvent extends DomainEvent {

    private String email;

}
