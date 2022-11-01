package com.alexxsnjr.cqrseventsource.user.infrastructure.dto;

import static java.util.Objects.isNull;

import com.alexxsnjr.cqrseventsource.user.UserType;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {

    private String id;
    @NotNull
    private String name;
    private String surname;
    @NotNull
    private String email;
    private String phone;
    @NotNull
    private UserType userType;

    public String getSurnameIfExist() {
        if (isNull(surname)) {
            return "";
        }
        return surname;
    }

    public String getPhoneIfExist() {
        if (isNull(phone)) {
            return "";
        }
        return phone;
    }
}
