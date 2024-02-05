package com.restapi.controller.admin;

import com.restapi.response.common.APIResponse;
import com.restapi.response.user.BookingResponse;
import com.restapi.service.admin.AdminBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/booking")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminBookingController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private AdminBookingService adminBookingService;

    // Retrieve all upcoming reservations for admin
    @GetMapping("/upcommingreservation")
    public ResponseEntity<APIResponse> getAllCurrentReservation(){
        // Call the service to get all upcoming reservations
        List<BookingResponse> upcommingReservation= adminBookingService.findAllUpcommingReservation();

        // Set the status and data in the API response
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(upcommingReservation);

        // Return the API response with HTTP status OK
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
