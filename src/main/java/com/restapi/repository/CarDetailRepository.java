package com.restapi.repository;

import com.restapi.model.CarDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public interface CarDetailRepository extends JpaRepository<CarDetail, Integer> {


    // Custom query to find a CarDetail by its id
    @Query("SELECT c FROM CarDetail c WHERE c.id = :carId")
    CarDetail findCarById(@Param("carId") Integer carId);

    // Custom native SQL query to find available CarDetail entities based on start and end dates
    @Query(value = "SELECT DISTINCT cd.* FROM car_detail cd " +
            "LEFT JOIN car_reservation cr ON cd.id = cr.vehicle_id " +
            "LEFT JOIN maintenance_schedule ms ON cd.id = ms.car_detail_id " +
            "WHERE (cr.start_date IS NULL OR cr.start_date > :end_date OR cr.end_date < :start_date) " +
            "AND (ms.maintenance_date IS NULL OR ms.maintenance_date > :end_date OR ms.maintenance_date < :start_date)",
            nativeQuery = true)
    List<CarDetail> findByAvailablity(@Param("start_date") Date startDate, @Param("end_date") Date endDate);

    // Custom query to delete CarDetail entities associated with a specific maintenance staff
    @Query("DELETE FROM CarDetail c WHERE c.maintenanceStaff.id=:id")
    void deleteByMaintenanceStaffId(@Param("id") Integer id);

    List<CarDetail> findByManufacturerInAndSeatsIn(List<String> manufacturers, List<Integer> seats);
    List<CarDetail> findByManufacturerIn(List<String> manufacturers);

    List<CarDetail> findBySeatsIn(List<Integer> seats);
}
