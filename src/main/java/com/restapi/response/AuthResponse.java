package com.restapi.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private Integer id;
    private String username;
    private String name;
    private String email;
    private String phone_number;
    private String address;
    private String role;
    private String password;
}
