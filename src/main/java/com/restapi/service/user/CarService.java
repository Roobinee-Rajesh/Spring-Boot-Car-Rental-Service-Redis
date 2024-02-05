package com.restapi.service.user;

import com.restapi.dto.CarDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.CarDetail;
import com.restapi.repository.CarDetailRepository;
import com.restapi.request.user.CarFilterRequest;
import com.restapi.request.user.CarRequest;
import com.restapi.response.user.CarResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {
    @Autowired
    private CarDto carDto;
    @Autowired
    private CarDetailRepository carDetailRepository;

    public List<CarDetail> findAllACars() {
        return carDetailRepository.findAll();
    }

    @Cacheable(value = "carDetails", key = "#carId.toString()")
    public Optional<CarDetail> findCarById(Integer carId) {
        return carDetailRepository.findById(carId);
    }


    public List<CarResponse> findAllAvailableCars(String start_date, String end_date) throws ParseException {
        SimpleDateFormat sdate = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdate.parse(start_date);
        Date endDate = sdate.parse(end_date);
//        return carDetailRepository.findByAvailablity(startDate,endDate);
        List<CarDetail> carDetails = carDetailRepository.findByAvailablity(startDate, endDate);
        return carDto.mapToCarResponse(carDetails);
    }

    public List<CarResponse> findAllFilteredCars(CarFilterRequest carFilterRequest) throws ParseException {
        List<CarResponse> carDetailList = findAllAvailableCars(carFilterRequest.getStart_date(), carFilterRequest.getEnd_date());
        // Apply filtering logic
        List<CarResponse> carDetailFilteredList = carDetailList.stream()
                .filter(car ->
                        (carFilterRequest.getRentalPricingRangeMin() == null || car.getRental_pricing() >= carFilterRequest.getRentalPricingRangeMin()) &&
                                (carFilterRequest.getRentalPricingRangeMax() == null || car.getRental_pricing() <= carFilterRequest.getRentalPricingRangeMax()) &&
                                (carFilterRequest.getManufacturer() == null || car.getManufacturer().equals(carFilterRequest.getManufacturer())) &&
                                (carFilterRequest.getModel() == null || car.getModel().equals(carFilterRequest.getModel())) &&
                                (carFilterRequest.getSeats() == null || car.getSeats() == carFilterRequest.getSeats()))
                .collect(Collectors.toList());
        return carDetailFilteredList;
    }
}
