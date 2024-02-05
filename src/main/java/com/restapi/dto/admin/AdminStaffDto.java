package com.restapi.dto.admin;

import com.restapi.model.AppUser;
import com.restapi.request.admin.AdminStaffRequest;
import org.springframework.stereotype.Component;

/**
 * Maps an AdminStaffRequest to an AppUser entity.
 * @@param adminStaffRequest The request object containing staff details.
 * @return An AppUser entity mapped from the request.
 **/

@Component
public class AdminStaffDto {
    public AppUser mapToAppUser(AdminStaffRequest adminStaffRequest) {
        AppUser appUser = new AppUser();
        appUser.setId(adminStaffRequest.getId());
        appUser.setName(adminStaffRequest.getName());
        appUser.setPassword(adminStaffRequest.getPassword());
        appUser.setUsername(adminStaffRequest.getUsername());
        appUser.setEmail(adminStaffRequest.getEmail());
        appUser.setPhone_number(adminStaffRequest.getPhone_number());
        appUser.setAddress(adminStaffRequest.getAddress());
        return appUser;
    }



}
