package com.restapi.response.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@Getter
@Setter
public class UserResponse {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String email;
    private Integer phone_number;
    private String address;
}
