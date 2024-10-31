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
    private DriverStatus status;

    public UpdateDriverDTO() {
        this.name = "";
        this.licenseNumber = null;
        this.phone = null;
        this.address = "";
        this.status = null;
    }

    public UpdateDriverDTO(Integer driverId, String name, String licenseNumber, String address, String phone, DriverStatus status) {
        this.driverId = driverId;
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.phone = phone;
        this.address = address;
        this.status = status;
    }
}

