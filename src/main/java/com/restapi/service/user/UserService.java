package com.restapi.service.user;

import com.restapi.dto.AuthDto;
import com.restapi.exception.common.InvalidUserException;
import com.restapi.model.AppUser;
import com.restapi.model.Role;
import com.restapi.repository.RoleRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.LoginRequest;
import com.restapi.request.RegisterRequest;
import com.restapi.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private AuthDto authDto;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
@Transactional
    public AuthResponse register(RegisterRequest registerRequest) {
        AppUser appUser = authDto.mapToAppUser(registerRequest);
//        System.out.println(appUser.getName());
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        appUser.setRoles(roleRepository.findByName(Role.USER));
        try {
            appUser = userRepository.save(appUser);
        }
        catch (Exception e){
            throw new InvalidUserException("User Already Exist");
        }
        return authDto.mapToAuthResponse(appUser);
    }

    public AuthResponse login(LoginRequest loginRequest) {
        AppUser appUser = userRepository
                .findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new InvalidUserException("Invalid user credentials"));

        if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), appUser.getPassword())) {
            throw new InvalidUserException("Invalid password");
        }

        return authDto.mapToAuthResponse(appUser);
    }

    public Optional<AppUser> findUserById(Integer userId) {
        Optional<AppUser> appUser = userRepository.findById(userId);
        return appUser;
    }

//    public AuthResponse updateUser(UserRequest userRequest) {
//        AppUser appUser = authDto.mapToAppUser(userRequest);
//        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
//        appUser.setRoles(roleRepository.findByName(Role.USER));
//        appUser = userRepository.save(appUser);
//        return authDto.mapToAuthResponse(appUser);
//    }
public AuthResponse updateUser(RegisterRequest registerRequest) {
    AppUser appUser = authDto.mapToAppUser(registerRequest);
//        System.out.println(appUser.getName());
    appUser.setRoles(roleRepository.findByName(Role.USER));
    appUser = userRepository.save(appUser);
    return authDto.mapToAuthResponse(appUser);
}

    public List<AuthResponse> findAllUsers() {
        List<AppUser> users= userRepository.findAllUsers();
        List<AuthResponse> userResponse=new ArrayList<>();
        for(AppUser user:users) {
             userResponse.add(authDto.mapToAuthResponse(user));
        }
        return userResponse;
    }
}
