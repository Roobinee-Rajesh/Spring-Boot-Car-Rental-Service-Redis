package com.restapi.dto.user;

import com.restapi.model.Notification;
import com.restapi.response.user.NotificationResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationDto {
    public List<NotificationResponse> mapToNotificationResponse(List<Notification> notifications) {
        List<NotificationResponse> notificationResponses=new ArrayList<>();
        for(Notification notification:notifications){
            NotificationResponse response = new NotificationResponse();
            response.setId(notification.getId()); // Assuming id is a field in Notification class
            response.setManufacturer(notification.getCarDetail().getManufacturer());
            response.setModel(notification.getCarDetail().getModel());
            response.setSeats(notification.getCarDetail().getSeats());
            response.setStartDate(String.valueOf(notification.getStartDate()));
            response.setEndDate(String.valueOf(notification.getEndDate()));

            notificationResponses.add(response);
        }
        return notificationResponses;
    }
}
