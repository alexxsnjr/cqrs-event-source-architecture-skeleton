package com.alexxsnjr.cqrseventsource.user.domain;

import com.alexxsnjr.cqrseventsource.user.UserActive;
import com.alexxsnjr.cqrseventsource.user.UserContact;
import com.alexxsnjr.cqrseventsource.user.UserId;
import com.alexxsnjr.cqrseventsource.user.UserName;
import com.alexxsnjr.cqrseventsource.user.UserType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {

    private UserId id;
    private UserContact contact;
    private UserName name;
    private UserActive active;
    private UserType type;


    public void changheMail(String email) {
        this.contact.setEmail(email);
    }
}
