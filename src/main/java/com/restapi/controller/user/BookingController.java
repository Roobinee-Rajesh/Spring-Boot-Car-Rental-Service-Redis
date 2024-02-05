package com.restapi.controller.user;

import com.restapi.model.CarReservation;
import com.restapi.request.user.CarRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.response.user.BookingResponse;
import com.restapi.service.user.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user/booking")
@PreAuthorize("hasRole('ROLE_USER')")
public class BookingController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private BookingService bookingService;

    // Get all future reservations of a user
    @GetMapping("/futurereservation/{userId}")
    public ResponseEntity<APIResponse> getAllFutureReservation(@PathVariable Integer userId){
        List<BookingResponse> userReservationList= bookingService.findAllFutureReservationOfUser(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userReservationList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Delete a reservation of a user
    @DeleteMapping("/delete/{reservationId}/{userId}")
    public ResponseEntity<APIResponse> deleteCar(@PathVariable Integer reservationId,@PathVariable Integer userId) {
        List<BookingResponse> userReservationList = bookingService.deleteById(reservationId,userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userReservationList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Get all past reservations of a user
    @GetMapping("/pastreservation/{userId}")
    public ResponseEntity<APIResponse> getAllPastReservation(@PathVariable Integer userId){
        List<BookingResponse> userReservationList= bookingService.findAllPastReservationOfUser(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userReservationList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Get all current reservations of a user
    @GetMapping("/currentreservation/{userId}")
    public ResponseEntity<APIResponse> getAllCurrentReservation(@PathVariable Integer userId){
        List<BookingResponse> userReservationList= bookingService.findAllCurrentReservationOfUser(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userReservationList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Book a car for a user
    @PostMapping("/bookCar")
    public ResponseEntity<APIResponse> getAllCurrentReservation(@RequestBody CarRequest carRequest){
        List<CarReservation> userReservationList= bookingService.bookCar(carRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userReservationList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
