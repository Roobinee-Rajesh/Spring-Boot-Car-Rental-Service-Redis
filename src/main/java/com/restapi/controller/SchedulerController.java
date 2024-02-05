package com.restapi.controller;

import com.restapi.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedule")
public class SchedulerController {

    @Autowired
    private SchedulerService schedulerService;

    // Trigger scheduler manually endpoint
    @GetMapping("/trigger-scheduler")
    public String triggerScheduler() {
        // Trigger the scheduler service to insert monthly data
        schedulerService.insertMonthlyData();
        return "Scheduler triggered manually.";
    }
}