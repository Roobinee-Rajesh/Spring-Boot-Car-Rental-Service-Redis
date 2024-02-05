package com.restapi.dto;

import com.restapi.model.AppUser;
import com.restapi.model.CarDetail;
import com.restapi.model.MaintenanceSchedule;
import com.restapi.model.MaintenanceStatus;
import com.restapi.repository.CarDetailRepository;
import com.restapi.repository.MaintenanceStatusRepository;
import com.restapi.repository.UserRepository;
import com.restapi.response.staff.MaintenanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MaintenanceDto {
    @Autowired
    private MaintenanceStatusRepository maintenanceStatusRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarDetailRepository carDetailRepository;

    /**
     * Maps a list of MaintenanceSchedule entities to a list of MaintenanceResponse DTOs.
     *
     * @param maintenanceScheduleList List of MaintenanceSchedule entities.
     * @return List of MaintenanceResponse DTOs mapped from MaintenanceSchedule entities.
     */
    public List<MaintenanceResponse> mapToMaintenanceResponse(List<MaintenanceSchedule> maintenanceScheduleList) {
        List<MaintenanceResponse> maintenanceResponseList = new ArrayList<>();
        for (MaintenanceSchedule maintenanceSchedule : maintenanceScheduleList) {
            MaintenanceResponse maintenanceResponse = new MaintenanceResponse();
            maintenanceResponse.setId(maintenanceSchedule.getId());
            maintenanceResponse.setMaintenanceDate(maintenanceSchedule.getMaintenance_date());

            // Fetch MaintenanceStatus details
            Optional<MaintenanceStatus> maintenanceStatus = maintenanceStatusRepository.findById(maintenanceSchedule.getMaintenanceStatus().getId());
            maintenanceResponse.setMaintenanceStatus(maintenanceStatus.get().getStatus());

            // Fetch AppUser details
            Optional<AppUser> appUser = userRepository.findById(maintenanceSchedule.getAppUser().getId());
            maintenanceResponse.setUserName(appUser.get().getName());

            // Fetch CarDetail details
            Optional<CarDetail> carDetail1 = carDetailRepository.findById(maintenanceSchedule.getCarDetail().getId());
            maintenanceResponse.setCarName(carDetail1.get().getModel());

            maintenanceResponseList.add(maintenanceResponse);
        }
        return maintenanceResponseList;

    }
}
