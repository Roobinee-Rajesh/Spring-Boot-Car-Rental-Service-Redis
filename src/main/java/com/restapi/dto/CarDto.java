package com.restapi.dto;

import com.restapi.model.CarDetail;
import com.restapi.response.user.CarResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarDto {

    /**
     * Maps a list of CarDetail entities to a list of CarResponse DTOs.
     *
     * @param carDetail List of CarDetail entities.
     * @return List of CarResponse DTOs mapped from CarDetail entities.
     */
    public List<CarResponse> mapToCarResponse(List<CarDetail> carDetail) {
        List<CarResponse> carResponseList = new ArrayList<>();
        for (CarDetail c : carDetail) {
            CarResponse carRespose = new CarResponse();
            carRespose.setId(c.getId());
            carRespose.setManufacturer(c.getManufacturer());
            carRespose.setModel(c.getModel());
            carRespose.setYear(c.getYear());
            carRespose.setSeats(c.getSeats());
            carRespose.setRental_pricing(c.getRental_pricing());
            carRespose.setPhoto(c.getPhoto());

            carResponseList.add(carRespose);
        }
        return carResponseList;
    }
}
