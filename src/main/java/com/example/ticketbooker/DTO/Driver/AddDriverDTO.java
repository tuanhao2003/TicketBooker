package com.example.ticketbooker.DTO.Driver;

import com.example.ticketbooker.Util.Enum.DriverStatus;
import lombok.Data;



@Data
public class AddDriverDTO {
    private String name;
    private String licenseNumber;
    private String phone;
    private String address;
    private DriverStatus status;

    public AddDriverDTO() {
        this.name = "";
        this.licenseNumber = "";
        this.phone = "";
        this.address = "";
        this.status = null;
    }

    public AddDriverDTO( String name, String licenseNumber, String address, String phone, DriverStatus status) {
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.phone = phone;
        this.address = address;
        this.status = status;
    }
}
