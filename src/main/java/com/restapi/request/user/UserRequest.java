package com.restapi.request.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restapi.model.CarDetail;
import com.restapi.model.CarReservation;
import com.restapi.model.MaintenanceSchedule;
import com.restapi.model.Role;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Getter
@Setter
public class UserRequest {
    private Integer id;
    @NotEmpty
    @Size(min = 2, message = "Username should have at least 2 characters")
    private String username;
    @NotEmpty
    @Size(min = 5, message = "Password should have at least 5 characters")
    private String password;
    @NotEmpty
    @Size(min = 2, message = "Name should have at least 2 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @NotEmpty
    @Size(min = 2, message = "Email should have at least 2 characters")
    private String email;

    @NotNull(message = "Phone number cannot be empty")
    private Integer phone_number;

    @NotEmpty
    @Size(min = 2, message = "Username should have at least 2 characters")
    private String address;

}
