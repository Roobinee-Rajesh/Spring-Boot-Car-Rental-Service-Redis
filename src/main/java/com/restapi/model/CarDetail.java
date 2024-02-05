package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@ToString
public class CarDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 200)
    private String manufacturer;

    @Column(nullable = false, length = 200)
    private String model;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private int seats;

    @Column(nullable = false)
    private int rental_pricing;

    @Column(nullable = false)
    private int maintenance_schedule;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_staff_id", referencedColumnName = "id")
    private AppUser maintenanceStaff;

    @Column(name = "photo")
    private String photo;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "carDetail")
    private List<CarReservation> carReservations;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "carDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaintenanceSchedule> maintenanceSchedules;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "carDetail")
    private List<Notification> notifications;
}
