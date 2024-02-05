package com.restapi.service.user;

import com.restapi.dto.user.NotificationDto;
import com.restapi.model.Notification;
import com.restapi.repository.NotificationRepository;
import com.restapi.response.user.NotificationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    NotificationDto notificationDto;
    private List<NotificationResponse> notificationResponses;

    @Transactional
    public void markAllUserNotificationsAsRead(Integer userId) {
        notificationRepository.markAllNotificationsAsRead(userId);
    }

    public List<NotificationResponse> getUserNotification(Integer userId) {
        List<Notification> notification = notificationRepository.getUnreadNotification(userId);
        return notificationDto.mapToNotificationResponse(notification);
    }
}
