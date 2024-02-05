package com.restapi.service.staff;

import com.restapi.dto.MaintenanceDto;
import com.restapi.exception.common.InvalidUserException;
import com.restapi.model.MaintenanceSchedule;
import com.restapi.model.MaintenanceStatus;
import com.restapi.repository.MaintenanceScheduleRepository;
import com.restapi.repository.MaintenanceStatusRepository;
import com.restapi.request.staff.MaintenanceRequest;
import com.restapi.response.staff.MaintenanceResponse;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service

public class MaintenanceService {
    @Autowired
    private MaintenanceScheduleRepository maintenanceScheduleRepository;

    @Autowired
    private MaintenanceStatusRepository maintenanceStatusRepository;
    @Autowired
    private MaintenanceResponse maintenanceResponse;
    @Autowired
    private MaintenanceDto maintenanceDto;

    public List<MaintenanceResponse> findMaintenanceByStaff(Integer staffId) {
        List<MaintenanceSchedule> maintenanceScheduleList = maintenanceScheduleRepository.findByStaffIdAndCurrentMonth(staffId);
        System.out.println(maintenanceScheduleList);
        return maintenanceDto.mapToMaintenanceResponse(maintenanceScheduleList);
    }


    @Transactional
    public List<MaintenanceSchedule> updateMaintenanceStaff(MaintenanceRequest maintenanceRequest) {
        MaintenanceSchedule maintenanceSchedule = maintenanceScheduleRepository.findById(maintenanceRequest.getId())
                .orElseThrow(() -> new InvalidUserException("Maintenance not Found"));
        MaintenanceStatus maintenanceStatus = maintenanceStatusRepository.findById(2);

        maintenanceSchedule.setMaintenanceStatus(maintenanceStatus);
        maintenanceScheduleRepository.save(maintenanceSchedule);
        return maintenanceScheduleRepository.findAll();
    }
}
