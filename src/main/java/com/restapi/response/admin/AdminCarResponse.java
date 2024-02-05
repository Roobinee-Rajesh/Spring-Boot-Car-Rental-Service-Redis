package com.restapi.response.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Getter
@Setter
@NoArgsConstructor
public class AdminCarResponse {
    private Integer id;
    private String manufacturer;
    private String model;
    private int year;
    private int seats;
    private double rental_pricing;
    private String photo;
    private Integer maintenance_staff_id;
    private String maintenance_staff;
    private int maintenance_schedule;
}
