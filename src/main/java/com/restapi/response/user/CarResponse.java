package com.restapi.response.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
@Getter
@Setter
public class CarResponse {
    private Integer id;
    private String manufacturer;
    private String model;
    private int year;
    private int seats;
    private double rental_pricing;
    private String photo;
}

