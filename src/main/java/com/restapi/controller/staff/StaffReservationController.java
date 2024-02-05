package com.restapi.controller.staff;

import com.restapi.model.Role;
import com.restapi.request.staff.BookingRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.response.user.BookingResponse;
import com.restapi.service.staff.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/staff/reservation")
//@PreAuthorize("hasRole('ROLE_STAFF')")
@RolesAllowed(Role.STAFF)
public class StaffReservationController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private ReservationService reservationService;

    // Get upcoming reservations assigned to a staff member
    @GetMapping("/staffReservation/{staffId}")
    public ResponseEntity<APIResponse> getStaffMaintenance(@PathVariable Integer staffId){
        List<BookingResponse> staffReservationList= reservationService.findUpCommingStaffReservation(staffId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(staffReservationList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Delete a reservation assigned to a staff member
    @PostMapping("/delete/{staffId}")
    public ResponseEntity<APIResponse> deleteCar(@RequestBody BookingRequest bookingRequest,@PathVariable Integer staffId) throws ParseException {
        System.out.println("in");
        List<BookingResponse> userReservationList = reservationService.deleteById(bookingRequest,staffId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userReservationList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
