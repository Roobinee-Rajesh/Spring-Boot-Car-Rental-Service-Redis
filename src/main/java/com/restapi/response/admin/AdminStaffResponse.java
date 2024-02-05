package com.restapi.response.admin;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class AdminStaffResponse {
    private Integer id;
    private String username;
    private String name;
    private String password;
    private String email;
    private Integer phone_number;
    private String address;

}
