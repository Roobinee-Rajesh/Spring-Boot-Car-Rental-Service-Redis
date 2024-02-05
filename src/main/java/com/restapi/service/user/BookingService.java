package com.restapi.service.user;

import com.restapi.dto.user.BookingDto;
import com.restapi.model.CarReservation;
import com.restapi.repository.CarDetailRepository;
import com.restapi.repository.CarReservationRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.user.CarRequest;
import com.restapi.response.user.BookingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    BookingDto bookingDto;
    @Autowired
    CarReservationRepository carReservationRepository;
    @Autowired
    CarDetailRepository carDetailRepository;

    @Autowired
    UserRepository userRepository;

    public List<BookingResponse> findAllFutureReservationOfUser(Integer userId) {
        Date currentDateAsDate=getCurrentDate();
        List<CarReservation> carReservation = carReservationRepository.findAllUpcomingReservationsOfUser(userId, currentDateAsDate);
        return bookingDto.mapToBookingResponse(carReservation);
    }

    public List<BookingResponse> deleteById(Integer reservationId, Integer userId) {
        carReservationRepository.deleteById(reservationId);
        return findAllFutureReservationOfUser(userId);
    }

    public List<BookingResponse> findAllPastReservationOfUser(Integer userId) {
        Date currentDateAsDate=getCurrentDate();
        List<CarReservation> carReservation = carReservationRepository.findAllPastReservationsOfUser(userId, currentDateAsDate);
        return bookingDto.mapToBookingResponse(carReservation);
    }


    public List<BookingResponse> findAllCurrentReservationOfUser(Integer userId) {
        Date currentDateAsDate=getCurrentDate();
        List<CarReservation> carReservation = carReservationRepository.findAllCurrentReservationsOfUser(userId, currentDateAsDate);
        return bookingDto.mapToBookingResponse(carReservation);
    }

    @Transactional
    public List<CarReservation> bookCar( CarRequest carRequest) {
        CarReservation carReservation = bookingDto.mapToCarReservation(carRequest);
        carReservationRepository.save(carReservation);
        return carReservationRepository.findAll();
    }

    public Date getCurrentDate() {
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = currentDate.format(formatter);
        Date currentDateAsDate = Date.from(currentDate.atZone(ZoneId.systemDefault()).toInstant());
        return currentDateAsDate;
    }
}
