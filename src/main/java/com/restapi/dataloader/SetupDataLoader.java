package com.restapi.dataloader;

import com.restapi.model.AppUser;
import com.restapi.model.MaintenanceStatus;
import com.restapi.model.Role;
import com.restapi.repository.MaintenanceStatusRepository;
import com.restapi.repository.RoleRepository;
import com.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MaintenanceStatusRepository maintenanceStatusRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // This method is triggered when the application context is refreshed
    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        // Check if the setup has already been executed to avoid duplication
        if (alreadySetup) {
            return;
        }

//        Create user roles
        Role userRole = createRoleIfNotFound(Role.USER);
        Role adminRole = createRoleIfNotFound(Role.ADMIN);
        Role staffRole = createRoleIfNotFound(Role.STAFF);
//        Create user
        createUserIfNotFound("user", "user@user.com", "user", "1234567891", "Chennai", userRole);
        createUserIfNotFound("admin", "admin@admin.com", "admin", "1234567892", "Theni", adminRole);
        createUserIfNotFound("staff", "staff@staff.com", "staff", "1234567893", "Chengalpet", staffRole);

        // Create maintenance status entries
        createMaintenanceStatusIfNotFound("Pending");
        createMaintenanceStatusIfNotFound("Maintenance Done");

        // Set the alreadySetup flag to true to indicate that the setup has been executed
        alreadySetup = true;
    }

    // Helper method to create or retrieve a maintenance status
    @Transactional
    private MaintenanceStatus createMaintenanceStatusIfNotFound(String status) {
        Optional<MaintenanceStatus> maintenanceStatusOptional = maintenanceStatusRepository.findByStatus(status);
        if (maintenanceStatusOptional.isPresent()) {
            return maintenanceStatusOptional.get();
        } else {
            MaintenanceStatus newMaintenanceStatus = new MaintenanceStatus(status);
            return maintenanceStatusRepository.save(newMaintenanceStatus);
        }
    }


    // Helper method to create or retrieve a role
    @Transactional
    private Role createRoleIfNotFound(final String username) {
        Role role = roleRepository.findByName(username);
        if (role == null) {
            role = new Role();
            role.setName(username);
            role = roleRepository.save(role);
        }
        return role;
    }

    // Helper method to create or retrieve a user
    @Transactional
    private AppUser createUserIfNotFound(final String username, final String email, final String password, final String phone_number, final String address,
                                         final Role role) {
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        AppUser user = null;
        if (optionalUser.isEmpty()) {
            user = new AppUser();
            user.setUsername(username);
            user.setName(username);
            user.setEmail(email);
            user.setAddress(address);
            user.setPhone_number(phone_number);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setRoles(role);
            user = userRepository.save(user);
        }
        return user;
    }
}
