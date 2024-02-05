package com.restapi.controller.user;

import com.restapi.model.AppUser;
import com.restapi.request.RegisterRequest;
import com.restapi.response.AuthResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.response.user.UserResponse;
import com.restapi.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private UserService userService;
    @Autowired
    private UserResponse userResponse;


    // Get user details by ID
    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUserById(@PathVariable Integer userId){
        Optional<AppUser> user= userService.findUserById(userId);
        if (user.isPresent()) {
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setData(user);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }


    // Update user details
    @PostMapping("/updateuser")
    public ResponseEntity<APIResponse> updateUser(@RequestBody RegisterRequest registerRequest){
        AuthResponse updateStaff=userService.updateUser(registerRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(updateStaff);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
