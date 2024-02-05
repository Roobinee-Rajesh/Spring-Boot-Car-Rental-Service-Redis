package com.restapi.repository;

import com.restapi.model.MaintenanceSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceScheduleRepository extends JpaRepository<MaintenanceSchedule,Integer> {
    // Custom query to find maintenance schedules for a specific staff member in the current month
    @Query("SELECT ms FROM MaintenanceSchedule ms " +
            "WHERE ms.appUser.id = :staffId " +
            "AND MONTH(ms.maintenance_date) = MONTH(CURRENT_DATE) " +
            "AND YEAR(ms.maintenance_date) = YEAR(CURRENT_DATE)" + "AND maintenanceStatus= 1")
    List<MaintenanceSchedule> findByStaffIdAndCurrentMonth(@Param("staffId") Integer staffId);


    // Custom query to find all maintenance schedules in the current month
    @Query("SELECT ms FROM MaintenanceSchedule ms " +
            "WHERE MONTH(ms.maintenance_date) = MONTH(CURRENT_DATE) " +
            "AND YEAR(ms.maintenance_date) = YEAR(CURRENT_DATE)")
    List<MaintenanceSchedule> findByCurrentMonth();
}
