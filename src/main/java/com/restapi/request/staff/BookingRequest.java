package com.restapi.request.staff;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class BookingRequest {
    private Integer id;
    private String reservation_date;
    private String start_date;
    private String end_date;
    private Integer total_price;
    private String model_name;
    private Integer userId;
    private Integer carId;
}
