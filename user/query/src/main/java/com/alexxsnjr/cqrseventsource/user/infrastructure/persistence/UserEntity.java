package com.alexxsnjr.cqrseventsource.user.infrastructure.persistence;

import com.alexxsnjr.cqrseventsource.user.UserType;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserEntity {

    @Id
    private String id;
    private boolean active;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private UserType userType;
}
