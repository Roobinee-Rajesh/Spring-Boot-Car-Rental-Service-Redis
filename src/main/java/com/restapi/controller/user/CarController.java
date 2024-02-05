package com.restapi.controller.user;

import com.restapi.model.CarDetail;
import com.restapi.request.user.CarFilterRequest;
import com.restapi.request.user.CarRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.response.user.CarResponse;
import com.restapi.service.user.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/car")
public class CarController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private CarService carService;

    @Autowired
    private CarRequest carRequest;

    // Get all cars
    @GetMapping("/allCars")
    public ResponseEntity<APIResponse> getCars() {
        List<CarDetail> allCarList = carService.findAllACars();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(allCarList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Get car details by ID
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/carById/{carId}")
    public ResponseEntity<APIResponse> findCarById(@PathVariable Integer carId) {
        Optional<CarDetail> carDetail = carService.findCarById(carId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(carDetail);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Get all available cars within a date range
    @GetMapping("/allAvailableCars/{start_date}/{end_date}")
    public ResponseEntity<APIResponse> getAllAvailableCars(@PathVariable String start_date,@PathVariable String end_date) throws ParseException {
        List<CarResponse> availableCarList = carService.findAllAvailableCars(start_date,end_date);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(availableCarList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Get filtered cars based on user preferences
    @PostMapping("/filteredCars")
    public ResponseEntity<APIResponse> getFilteredCars(@RequestBody CarFilterRequest carFilterRequest) throws ParseException {
        System.out.println("hi");
        List<CarResponse> availableCarList = carService.findAllFilteredCars(carFilterRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(availableCarList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
