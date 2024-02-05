package com.restapi.service.admin;

import com.restapi.dto.admin.AdminCarDto;
import com.restapi.model.AppUser;
import com.restapi.model.CarDetail;
import com.restapi.repository.CarDetailRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.admin.AdminCarFilterRequest;
import com.restapi.request.admin.AdminCarRequest;
import com.restapi.response.admin.AdminCarResponse;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AdminCarService {
    @Autowired
    private AdminCarDto adminCarDto;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarDetailRepository carDetailRepository;

    @Transactional
    @Caching(
            put = {@CachePut(value = "carDetails", key = "'allCars'")},
            cacheable = {@Cacheable(value = "carDetails", key = "'allCars'")}
    )
    public List<CarDetail> addCar(AdminCarRequest carRequest) {
        CarDetail carDetail = adminCarDto.mapToCarDetail(carRequest);
        carDetail.setPhoto(carRequest.getPhoto());
        Optional<AppUser> appUser = userRepository.findById(Integer.valueOf(carRequest.getMaintenance_staff_id()));
        carDetail.setMaintenanceStaff(appUser.get());
        CarDetail carDetailReturn = carDetailRepository.save(carDetail);
        return findAllCars();
    }

    @Transactional
    @Cacheable(value = "carDetails", key = "'allCars'")
    public List<CarDetail> findAllCars() {
        List<CarDetail> carDetailsList = carDetailRepository.findAll();
        for (CarDetail carDetail : carDetailsList) {
            Hibernate.initialize(carDetail.getMaintenanceStaff());
        }
        return carDetailsList;
    }

    @Caching(evict = {
            @CacheEvict(value = "carDetails", key = "'allCars'"),
            @CacheEvict(value = "carDetails", key = "#id")
    })
    public void deleteById(Integer id) {
        carDetailRepository.deleteById(id);
    }

    @Caching(
            evict = {@CacheEvict(value = "carDetails", allEntries = true)},
            put = {@CachePut(value = "carDetails", key = "#adminCarRequest.id")}
    )
    @Transactional
    public CarDetail updateCar(AdminCarRequest adminCarRequest) {
        CarDetail carDetail = adminCarDto.mapToCarDetails(adminCarRequest);

        CarDetail existingCarDetail = carDetailRepository.findById(adminCarRequest.getId())
                .orElseThrow(() -> new EntityNotFoundException("CarDetail not found"));

        carDetail.setMaintenanceSchedules(new ArrayList<>()); // Clear existing schedules

        carDetail.getMaintenanceSchedules().addAll(existingCarDetail.getMaintenanceSchedules());
        AppUser maintenanceStaff = userRepository.findById(Integer.valueOf(adminCarRequest.getMaintenance_staff_id()))
                .orElseThrow(() -> new RuntimeException("Maintenance staff not found"));

        carDetail.setMaintenanceStaff(maintenanceStaff);

        if (adminCarRequest.getPhoto() == null) {
            CarDetail carDetailImage = carDetailRepository.findCarById(adminCarRequest.getId());
            carDetail.setPhoto(carDetailImage.getPhoto());
        } else {
            carDetail.setPhoto(adminCarRequest.getPhoto());
        }

        carDetail = carDetailRepository.save(carDetail);
        return carDetail;
    }


    @Transactional
    public List<AdminCarResponse> filterByManufacturerAndSeats(AdminCarFilterRequest adminCarFilterRequest) {
        List<CarDetail> carDetail;
        if (adminCarFilterRequest.getManufacturer() != null && !adminCarFilterRequest.getManufacturer().isEmpty()
                && adminCarFilterRequest.getSeat() != null && !adminCarFilterRequest.getSeat().isEmpty()) {
            // Both manufacturer and seats are provided
            carDetail = carDetailRepository.findByManufacturerInAndSeatsIn(
                    adminCarFilterRequest.getManufacturer(),
                    adminCarFilterRequest.getSeat()
            );
        } else if (adminCarFilterRequest.getManufacturer() != null && !adminCarFilterRequest.getManufacturer().isEmpty()) {
            // Only manufacturer is provided
            carDetail = carDetailRepository.findByManufacturerIn(adminCarFilterRequest.getManufacturer());
        } else if (adminCarFilterRequest.getSeat() != null && !adminCarFilterRequest.getSeat().isEmpty()) {
            // Only seats are provided
            carDetail = carDetailRepository.findBySeatsIn(adminCarFilterRequest.getSeat());
        } else {
            // No filters provided, return all cars
            carDetail = carDetailRepository.findAll();
        }

        return adminCarDto.mapToAdminCarResponse(carDetail);
    }
}


