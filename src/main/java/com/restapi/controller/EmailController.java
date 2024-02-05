package com.restapi.controller;

import com.restapi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    // Send email endpoint
    @PostMapping("/send-email")
    public String sendEmail(@RequestBody String email) {
        // Extracted email address from the request body
        String to = email;

        // Email subject and body
        String subject = "Car Booking Confirmation";
        String body = "Your booing has been confirmed";

        // Send a simple email using the EmailService
        emailService.sendSimpleEmail(to, subject, body);

        return "Email sent successfully!";
    }
}
