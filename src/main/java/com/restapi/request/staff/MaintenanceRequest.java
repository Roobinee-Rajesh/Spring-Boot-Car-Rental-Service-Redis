package com.restapi.request.staff;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Date;
@Component
@Getter
@Setter
public class MaintenanceRequest {
    private Integer id;
    private String maintenanceDate;
    private String maintenanceStatus;
    private String userId;
    private String carDetailId;
}
