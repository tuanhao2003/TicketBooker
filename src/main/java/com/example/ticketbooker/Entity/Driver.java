package com.example.ticketbooker.Entity;

import com.example.ticketbooker.Util.Enum.DriverStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driverId", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "licenseNumber", nullable = false, length = 20)
    private String licenseNumber;

    @Column(name = "phone", length = 11)
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "driverStatus", nullable = false)
    @Enumerated(EnumType.STRING)
    private DriverStatus driverStatus;

    public Driver() {
        this.id = null;
        this.name = "";
        this.licenseNumber = "";
        this.phone = null;
        this.address = null;
        this.driverStatus = DriverStatus.ACTIVE;
    }

    public Driver(Integer id, String name, String licenseNumber, String phone, String address, DriverStatus driverStatus) {
        this.id = id;
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.phone = phone;
        this.address = address;
        this.driverStatus = driverStatus;
    }
}