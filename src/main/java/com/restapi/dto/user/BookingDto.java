package com.restapi.dto.user;

import com.restapi.model.AppUser;
import com.restapi.model.CarDetail;
import com.restapi.model.CarReservation;
import com.restapi.repository.CarDetailRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.user.CarRequest;
import com.restapi.response.user.BookingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class BookingDto {
    @Autowired
    private CarDetailRepository carDetailRepository;
    @Autowired
    private UserRepository userRepository;


    /**
     * Maps a CarRequest to a CarReservation entity, calculating the total price based on rental pricing and duration.
     * @param carRequest The request object containing car reservation details.
     * @return A CarReservation entity mapped from the request.
     */
    public CarReservation mapToCarReservation(CarRequest carRequest) {
        CarReservation carReservation = new CarReservation();
        Optional<CarDetail> carDetail=carDetailRepository.findById(carRequest.getCarId());
        Optional<AppUser> appUser=userRepository.findById(carRequest.getUserId());
        int total_price;
        SimpleDateFormat sdate = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = sdate.parse(carRequest.getStart_date());
            Date endDate = sdate.parse(carRequest.getEnd_date());
            Duration duration = Duration.between(startDate.toInstant(), endDate.toInstant());
            long days = duration.toDays();
            total_price= (int) (days*(carDetail.get().getRental_pricing()));
            carReservation.setStart_date(startDate);
            carReservation.setEnd_date(endDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        carReservation.setTotal_price(total_price);
        carReservation.setCarDetail(carDetail.get());
        carReservation.setAppUser(appUser.get());


        return carReservation;

    }


    /**
     * Maps a list of CarReservation entities to a list of BookingResponse DTOs.
     * @@param carReservations The list of CarReservation entities.
     * @return A list of BookingResponse DTOs.
     */
    public List<BookingResponse> mapToBookingResponse(List<CarReservation> carReservation) {
        List<BookingResponse> bookingResponses = new ArrayList<>();


        for (CarReservation c : carReservation) {
            BookingResponse bookingResponse = new BookingResponse();
            bookingResponse.setReservation_date(c.getReservation_date());
            bookingResponse.setStart_date(c.getStart_date());
            bookingResponse.setEnd_date(c.getEnd_date());
            bookingResponse.setModel_name(c.getCarDetail().getModel());
            bookingResponse.setCarId(c.getCarDetail().getId());
            bookingResponse.setTotal_price(c.getTotal_price());
            bookingResponse.setId(c.getId());
            bookingResponse.setUserId(c.getAppUser().getId());
            bookingResponses.add(bookingResponse);
            System.out.println(bookingResponse);
        }

        return bookingResponses;
    }
}
