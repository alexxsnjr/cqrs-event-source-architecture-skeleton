package com.alexxsnjr.cqrseventsource.user;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserActive {

    private boolean active;

}
