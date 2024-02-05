package com.restapi.request.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDateTime;

@Component
@Getter
@Setter
public class CarRequest {
    @NotNull(message = "User ID can't be empty")
    private int userId;

    @NotNull(message = "Car ID can't be empty")
    private int carId;
    @NotEmpty(message = "Start date can't be empty")
    private String  start_date;

    @NotEmpty(message = "End date can't be empty")
    private String  end_date;
}
