package com.restapi.response.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationResponse {
    private Integer id;
    private String manufacturer;
    private String model;
    private Integer seats;
    private String startDate;
    private String endDate;
}
