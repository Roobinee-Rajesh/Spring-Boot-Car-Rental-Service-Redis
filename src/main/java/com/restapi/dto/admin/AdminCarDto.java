package com.restapi.dto.admin;

import com.restapi.model.CarDetail;
import com.restapi.request.admin.AdminCarRequest;
import com.restapi.response.admin.AdminCarResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminCarDto {

    // Maps an AdminCarRequest to a CarDetail entity
    public CarDetail mapToCarDetail(AdminCarRequest carRequest) {
        CarDetail carDetail = new CarDetail();
        carDetail.setModel(carRequest.getModel());
        carDetail.setManufacturer(carRequest.getManufacturer());
        carDetail.setYear(carRequest.getYear());
        carDetail.setSeats(carRequest.getSeats());
        carDetail.setRental_pricing(carRequest.getRental_pricing());
        carDetail.setPhoto(carRequest.getPhoto());
        carDetail.setMaintenance_schedule(carRequest.getMaintenance_schedule());
        return carDetail;
    }

    // Maps a CarDetail entity to an AdminCarResponse
    public AdminCarResponse mapToCarResponse(CarDetail carDetail){
        AdminCarResponse adminCarResponse=new AdminCarResponse();
        adminCarResponse.setModel(carDetail.getModel());
        adminCarResponse.setManufacturer(carDetail.getManufacturer());
        adminCarResponse.setYear(carDetail.getYear());
        adminCarResponse.setSeats(carDetail.getSeats());
        adminCarResponse.setRental_pricing(carDetail.getRental_pricing());
        adminCarResponse.setPhoto(carDetail.getPhoto());
        adminCarResponse.setMaintenance_staff(carDetail.getMaintenanceStaff().getName());
        adminCarResponse.setMaintenance_schedule(carDetail.getMaintenance_schedule());
        return adminCarResponse;
    }

    // Maps an AdminCarRequest to a CarDetail entity (used for updating)
    public CarDetail mapToCarDetails(AdminCarRequest adminCarRequest) {
        CarDetail carDetail=new CarDetail();
        carDetail.setId(adminCarRequest.getId());
        carDetail.setModel(adminCarRequest.getModel());
        carDetail.setManufacturer(adminCarRequest.getManufacturer());
        carDetail.setYear(adminCarRequest.getYear());
        carDetail.setSeats(adminCarRequest.getSeats());
        carDetail.setRental_pricing(adminCarRequest.getRental_pricing());
        carDetail.setMaintenance_schedule(adminCarRequest.getMaintenance_schedule());
        return carDetail;
    }

    // Maps a list of CarDetail entities to a list of AdminCarResponse
    public List<AdminCarResponse> mapToAdminCarResponse(List<CarDetail> carDetailsList) {
        List<AdminCarResponse> adminCarResponses = new ArrayList<>();

        for (CarDetail carDetail : carDetailsList) {
            AdminCarResponse adminCarResponse = new AdminCarResponse();
            adminCarResponse.setId(carDetail.getId());
            adminCarResponse.setModel(carDetail.getModel());
            adminCarResponse.setManufacturer(carDetail.getManufacturer());
            adminCarResponse.setYear(carDetail.getYear());
            adminCarResponse.setSeats(carDetail.getSeats());
            adminCarResponse.setRental_pricing(carDetail.getRental_pricing());
            adminCarResponse.setPhoto(carDetail.getPhoto());
            adminCarResponse.setMaintenance_schedule(carDetail.getMaintenance_schedule());
            adminCarResponse.setMaintenance_staff_id(carDetail.getMaintenanceStaff().getId());
            adminCarResponse.setMaintenance_staff(carDetail.getMaintenanceStaff().getName());
            adminCarResponses.add(adminCarResponse);
        }

        return adminCarResponses;
    }
    }
