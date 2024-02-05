package com.restapi.repository;

import com.restapi.model.Notification;
import com.restapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Notification n SET n.isRead = 1 WHERE n.isRead = 0 and n.appUser.id=:userId")
    void markAllNotificationsAsRead(@Param("userId") Integer userId);

    @Query("SELECT n FROM Notification n WHERE n.isRead = 0 AND n.appUser.id=:userId")
    List<Notification> getUnreadNotification(Integer userId);
}
