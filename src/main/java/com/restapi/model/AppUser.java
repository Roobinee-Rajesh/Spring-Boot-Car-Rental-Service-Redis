package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "users") // don't use User
//@RedisHash("AppUser")
public class AppUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", unique = true, nullable = false, length = 100)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 13)
    private String phone_number;

    @Column(nullable = false, length = 100)
    private String address;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role roles;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "appUser")
    private List<CarReservation> carReservations;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "appUser")
    private List<MaintenanceSchedule> maintenanceSchedulesUser;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "appUser")
    private List<Notification> notifications;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "maintenanceStaff")
    private List<CarDetail> carDetails;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

}
