package com.alexxsnjr.cqrseventsource.user;

import com.alexxsnjr.cqrseventsource.domain.Identifier;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserId extends Identifier {

    @Builder
    public UserId(String value) {
        super(value);
    }
}
