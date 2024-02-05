package com.restapi.response.staff;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.restapi.model.AppUser;
import com.restapi.model.CarDetail;
import com.restapi.model.MaintenanceSchedule;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Component
@Getter
@Setter
public class MaintenanceResponse {
    private Integer id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date maintenanceDate;
    private String maintenanceStatus;
    private String userName;
    private String carName;
}
