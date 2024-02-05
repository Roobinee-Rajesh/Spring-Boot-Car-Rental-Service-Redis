package com.restapi.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
public class MaintenanceStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 200)
    private String status;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "maintenanceStatus")
    private List<MaintenanceSchedule> maintenanceSchedules;


    public MaintenanceStatus(String status) {
        this.status = status;
    }
}
