package com.restapi.service.admin;

import com.restapi.dto.AuthDto;
import com.restapi.dto.admin.AdminStaffDto;
import com.restapi.model.AppUser;
import com.restapi.model.Role;
import com.restapi.repository.CarDetailRepository;
import com.restapi.repository.CarReservationRepository;
import com.restapi.repository.RoleRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.RegisterRequest;
import com.restapi.request.admin.AdminStaffRequest;
import com.restapi.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminStaffService {
    @Autowired
    private AuthDto authDto;
    @Autowired
    private AdminStaffDto adminStaffDto;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CarReservationRepository carReservationRepository;
    @Autowired
    private CarDetailRepository carDetailRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<AuthResponse> findAllStaffMembers() {
        List<AppUser> appUsers = userRepository.findAllStaffMembers();
        List<AuthResponse> authResponses = new ArrayList<>();

        for (AppUser appUser : appUsers) {
            AuthResponse authResponse = authDto.mapToAuthResponse(appUser);
            authResponses.add(authResponse);
        }

        return authResponses;
    }

    @Transactional
    public AuthResponse registerStaff(RegisterRequest registerRequest) {
        AppUser appUser = authDto.mapToAppUser(registerRequest);
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        appUser.setRoles(roleRepository.findByName(Role.STAFF));
        appUser = userRepository.save(appUser);
        return authDto.mapToAuthResponse(appUser);
    }

    public List<AuthResponse> deleteById(Integer id) {
//        carDetailRepository.deleteByMaintenanceStaffId(id);
        userRepository.deleteById(id);
        return findAllStaffMembers();
    }


    @Transactional
    public AppUser updateStaff(AdminStaffRequest adminStaffRequest) {
        AppUser appUser = adminStaffDto.mapToAppUser(adminStaffRequest);
//        if (adminStaffRequest.getPassword() != null) {
//            appUser.setPassword(bCryptPasswordEncoder.encode(adminStaffRequest.getPassword()));
//        }
        appUser.setRoles(roleRepository.findByName(Role.STAFF));
        appUser = userRepository.save(appUser);
        return appUser;
    }
}
