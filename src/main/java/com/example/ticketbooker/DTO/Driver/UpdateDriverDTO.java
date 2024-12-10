package com.example.ticketbooker.DTO.Driver;

import com.example.ticketbooker.Util.Enum.DriverStatus;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class UpdateDriverDTO {
    private Integer driverId;
    private String name;
    private String licenseNumber;
    private String phone;
    private String address;
    private DriverStatus driverStatus;

    public UpdateDriverDTO() {
        this.driverId = null;
        this.name = "";
        this.licenseNumber = null;
        this.phone = null;
        this.address = "";
        this.driverStatus = null;
    }

    public UpdateDriverDTO(Integer driverId, String name, String licenseNumber, String phone, String address, DriverStatus status) {
        this.driverId = driverId;
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.phone = phone;
        this.address = address;
        this.driverStatus = status;
    }
}

