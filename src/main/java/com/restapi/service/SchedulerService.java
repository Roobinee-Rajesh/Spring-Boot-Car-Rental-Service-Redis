package com.restapi.service;

import com.restapi.model.CarDetail;
import com.restapi.model.MaintenanceSchedule;
import com.restapi.model.MaintenanceStatus;
import com.restapi.repository.CarDetailRepository;
import com.restapi.repository.MaintenanceScheduleRepository;
import com.restapi.repository.MaintenanceStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class SchedulerService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CarDetailRepository carDetailRepository;

    @Autowired
    private MaintenanceScheduleRepository maintenanceScheduleRepository;

    @Autowired
    private MaintenanceStatusRepository maintenanceStatusRepository;

    @Scheduled(cron = "${scheduled.job.cron.expression}")
    public void insertMonthlyData() {

        List<CarDetail> carDetailsWithSchedules = carDetailRepository.findAll();


        for (CarDetail carDetail : carDetailsWithSchedules) {
            MaintenanceSchedule maintenanceSchedule = new MaintenanceSchedule();
            maintenanceSchedule.setMaintenance_date(calculateMaintenanceDate(carDetail.getMaintenance_schedule()));
            MaintenanceStatus pendingStatus = maintenanceStatusRepository.findById(1);
            maintenanceSchedule.setMaintenanceStatus(pendingStatus);
            maintenanceSchedule.setAppUser(carDetail.getMaintenanceStaff());
            maintenanceSchedule.setCarDetail(carDetail);

            maintenanceScheduleRepository.save(maintenanceSchedule);
        }
    }

    private Date calculateMaintenanceDate(int maintenanceScheduleDays) {

        LocalDate currentDate = LocalDate.now();
        return Date.from(currentDate.plusDays(maintenanceScheduleDays).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
}
