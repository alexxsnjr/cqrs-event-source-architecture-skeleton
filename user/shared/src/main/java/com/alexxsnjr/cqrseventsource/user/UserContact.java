package com.alexxsnjr.cqrseventsource.user;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class UserContact {

    private String email;
    private String phone;
}
