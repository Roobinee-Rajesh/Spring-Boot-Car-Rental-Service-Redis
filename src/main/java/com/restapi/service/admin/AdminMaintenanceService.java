package com.restapi.service.admin;

import com.restapi.dto.MaintenanceDto;
import com.restapi.model.MaintenanceSchedule;
import com.restapi.repository.MaintenanceScheduleRepository;
import com.restapi.response.staff.MaintenanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminMaintenanceService {
    @Autowired
    private MaintenanceScheduleRepository maintenanceScheduleRepository;

    @Autowired
    private MaintenanceDto maintenanceDto;


    public List<MaintenanceResponse> findAllCurrentMonthMaintenance() {
        List<MaintenanceSchedule> maintenanceScheduleList= maintenanceScheduleRepository.findByCurrentMonth();
//        System.out.println(maintenanceDto.mapToMaintenanceResponse(maintenanceScheduleList));
        return maintenanceDto.mapToMaintenanceResponse(maintenanceScheduleList);
    }
}
