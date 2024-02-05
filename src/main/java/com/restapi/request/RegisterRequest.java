package com.restapi.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Getter
@Setter
public class RegisterRequest {

    private Integer id;

    @NotEmpty(message = "Username can't be empty")
    @Size(min = 2, message = "Username should have at least 2 characters")
    private String userName;

    @NotEmpty(message = "Password can't be empty")
    @Size(min = 2, message = "Password should have at least 2 characters")
    private String password;

    @NotEmpty(message = "Name can't be empty")
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Address cannot be empty")
    private String address;

//    @NotEmpty(message = "Phone number cannot be empty")
    private String phoneNumber;
}
