package com.restapi.controller.user;

import com.restapi.model.Notification;
import com.restapi.response.common.APIResponse;
import com.restapi.response.user.NotificationResponse;
import com.restapi.service.user.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping("/api/user/notification")
public class NotificationController {
    @Autowired
    private APIResponse apiResponse;
    @Autowired
    private NotificationService notificationService;

    @PutMapping
    public ResponseEntity<APIResponse> markAllUserNotificationsAsRead(@RequestBody Integer userId){
        notificationService.markAllUserNotificationsAsRead(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setData();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/getNotification/{userId}")
    public ResponseEntity<APIResponse> getUserNotification(@PathVariable Integer userId){
        List<NotificationResponse> notification=notificationService.getUserNotification(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(notification);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
