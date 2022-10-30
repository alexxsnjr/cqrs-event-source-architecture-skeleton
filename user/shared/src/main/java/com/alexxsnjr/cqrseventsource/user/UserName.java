package com.alexxsnjr.cqrseventsource.user;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserName {

    private String name;
    private String surname;

}
