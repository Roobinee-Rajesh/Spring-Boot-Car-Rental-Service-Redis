package com.restapi.repository;

import com.restapi.model.CarReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface CarReservationRepository extends JpaRepository<CarReservation, Integer> {
    // Custom query to find all upcoming reservations of a user
    @Query("SELECT c FROM CarReservation c WHERE c.appUser.id = :user_id AND c.start_date > :current_date")
    List<CarReservation> findAllUpcomingReservationsOfUser(@Param("user_id") Integer userId, @Param("current_date") Date currentDate);

    // Custom query to find all past reservations of a user
    @Query("SELECT c FROM CarReservation c WHERE c.appUser.id = :user_id AND c.end_date < :current_date")
    List<CarReservation> findAllPastReservationsOfUser(@Param("user_id") Integer userId, @Param("current_date") Date currentDate);

    // Custom query to find all current reservations of a user
    @Query("SELECT c FROM CarReservation c WHERE c.appUser.id = :user_id AND c.start_date <= :current_date AND c.end_date >= :current_date")
    List<CarReservation> findAllCurrentReservationsOfUser(@Param("user_id") Integer userId, @Param("current_date") Date currentDate);

    // Custom query to find all upcoming reservations starting from the current date
    @Query("SELECT c FROM CarReservation c WHERE c.start_date >= :current_date")
    List<CarReservation> findAllUpcommingReservation(@Param("current_date") Date currentDate);

    // Custom query to find all upcoming reservations for a staff member
    @Query("SELECT cr FROM CarReservation cr " +
            "JOIN cr.carDetail cd " +
            "WHERE cd.maintenanceStaff.id = :staffId " +
            "AND MONTH(cr.start_date) >= MONTH(CURRENT_DATE) " +
            "AND DATE(cr.start_date) > DATE(CURRENT_DATE)")
    List<CarReservation> findAllUpcommingReservationForStaff(@Param("staffId") Integer staffId);

}