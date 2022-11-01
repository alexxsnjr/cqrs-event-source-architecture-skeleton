package com.alexxsnjr.cqrseventsource.user;

import com.alexxsnjr.cqrseventsource.domain.event.DomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class UserDeletedEvent extends DomainEvent {

}
