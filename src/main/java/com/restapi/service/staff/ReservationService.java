package com.restapi.service.staff;

import com.restapi.dto.user.BookingDto;
import com.restapi.model.CarReservation;
import com.restapi.model.Notification;
import com.restapi.repository.CarDetailRepository;
import com.restapi.repository.CarReservationRepository;
import com.restapi.repository.NotificationRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.staff.BookingRequest;
import com.restapi.response.user.BookingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    CarReservationRepository carReservationRepository;
    @Autowired
    CarDetailRepository carDetailRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    BookingDto bookingDto;

    @Autowired
    NotificationRepository notificationRepository;
    public List<BookingResponse> findUpCommingStaffReservation(Integer staffId) {
        List<CarReservation> staffCarReservationList=carReservationRepository.findAllUpcommingReservationForStaff(staffId);
        return bookingDto.mapToBookingResponse(staffCarReservationList);
    }

    public List<BookingResponse> deleteById(BookingRequest bookingRequest,Integer staffId) throws ParseException {

        System.out.println("service");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse(bookingRequest.getStart_date());
        Date endDate = dateFormat.parse(bookingRequest.getEnd_date());

        Notification notification=new Notification();
        notification.setStartDate(startDate);
        notification.setEndDate(endDate);
        notification.setPrice(bookingRequest.getTotal_price());
        notification.setCarDetail(carDetailRepository.findById(bookingRequest.getCarId()).get());
        notification.setAppUser(userRepository.findById(bookingRequest.getUserId()).get());
        notification.setIsRead(0);
        notificationRepository.save(notification);
        carReservationRepository.deleteById(bookingRequest.getId());
        return findUpCommingStaffReservation(staffId);
    }
}
