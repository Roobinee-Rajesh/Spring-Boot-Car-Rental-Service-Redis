package com.restapi.service.admin;

import com.restapi.dto.user.BookingDto;
import com.restapi.model.CarReservation;
import com.restapi.repository.CarReservationRepository;
import com.restapi.response.user.BookingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service

public class AdminBookingService {
    @Autowired
    private CarReservationRepository carReservationRepository;
    @Autowired
    private BookingDto bookingDto;

    public List<BookingResponse> findAllUpcommingReservation() {
        Date currentDateAsDate=getCurrentDate();
        List<CarReservation> carReservation = carReservationRepository.findAllUpcommingReservation(currentDateAsDate);
        System.out.println(carReservation);
        return bookingDto.mapToBookingResponse(carReservation);
    }

    public Date getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDateAsDate = LocalDate.parse(currentDate.toString(),formatter);
        Date currentDateAsLegacyDate = java.sql.Date.valueOf(currentDateAsDate);
        return currentDateAsLegacyDate;
    }
}
