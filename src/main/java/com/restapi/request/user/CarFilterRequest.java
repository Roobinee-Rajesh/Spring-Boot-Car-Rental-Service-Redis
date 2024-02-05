package com.restapi.request.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class CarFilterRequest {
    private Integer rentalPricing;
    private Integer rentalPricingRangeMin;
    private Integer rentalPricingRangeMax;
    private String manufacturer;
    private String model;
    private Integer seats;
    private String start_date;
    private String end_date;
}
