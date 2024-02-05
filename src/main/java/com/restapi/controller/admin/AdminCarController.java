package com.restapi.controller.admin;

import com.restapi.dto.admin.AdminCarDto;
import com.restapi.model.CarDetail;
import com.restapi.request.admin.AdminCarFilterRequest;
import com.restapi.request.admin.AdminCarRequest;
import com.restapi.response.admin.AdminCarResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.StorageService;
import com.restapi.service.admin.AdminCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin/car")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminCarController {

    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private AdminCarService adminCarService;
    @Autowired
    private AdminCarResponse adminCarResponse;
    @Autowired
    private AdminCarDto adminCarDto;

    @Autowired
    private StorageService storageService;

    // Get all cars for admin view
    @GetMapping("/viewCars")
    public ResponseEntity<APIResponse> getAllCar() {
        List<CarDetail> carDetailsList = adminCarService.findAllCars();
        List<AdminCarResponse> adminCarResponse = adminCarDto.mapToAdminCarResponse(carDetailsList);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(adminCarResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Add a new car with photo upload
    @PostMapping(consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity<APIResponse> addCar(
            @ModelAttribute AdminCarRequest adminCarRequest
    ) throws IOException {
        // Store the uploaded photo and set the file path in the request
        String file = storageService.storeFile(adminCarRequest.getPhotoFile());
        adminCarRequest.setPhoto(file);
        // Add the car and get the updated car list
        List<CarDetail> carDetail = adminCarService.addCar(adminCarRequest);

        List<AdminCarResponse> adminCarResponse= adminCarDto.mapToAdminCarResponse(carDetail);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(adminCarResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Update an existing car
    @PostMapping("/updateCar")
    public ResponseEntity<APIResponse> updateCar(@RequestBody AdminCarRequest adminCarRequest) {
        // Update the car and get the updated car details
        CarDetail updateCar = adminCarService.updateCar(adminCarRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(updateCar);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Delete a car by its ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse> deleteCar(@PathVariable Integer id) {
        // Delete the car and get the updated car list
        adminCarService.deleteById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setData(carList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //Filter Car
    @PostMapping("/adminCarFilter")
    public ResponseEntity<APIResponse> carFilter(@RequestBody AdminCarFilterRequest adminCarFilterRequest) {
        List<AdminCarResponse> carList = adminCarService.filterByManufacturerAndSeats(adminCarFilterRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(carList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
