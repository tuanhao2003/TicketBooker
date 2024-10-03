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
    private int driverId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "licenseNumber", nullable = false)
    private String licenseNumber;

    @Column(name = "phone", nullable = true)
    private String phone;

    @Column(name = "address", nullable = true)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = true)
    private DriverStatus status;

    public Driver() {
        this.driverId = 0;
        this.name = "";
        this.licenseNumber = null;
        this.phone = null;
        this.address = "";
        this.status = DriverStatus.Active;
    }

    public Driver(int driverId, String name, String licenseNumber, String address, String phone, DriverStatus status) {
        this.driverId = driverId;
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.phone = phone;
        this.address = address;
        this.status = status;
    }
}
