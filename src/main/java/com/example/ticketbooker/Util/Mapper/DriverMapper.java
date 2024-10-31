package com.example.ticketbooker.Util.Mapper;

import com.example.ticketbooker.DTO.Driver.AddDriverDTO;
import com.example.ticketbooker.DTO.Driver.ResponseDriverDTO;
import com.example.ticketbooker.DTO.Driver.UpdateDriverDTO;
import com.example.ticketbooker.Entity.Driver;

import java.util.ArrayList;

public class DriverMapper {
    public static Driver fromAdd (AddDriverDTO dto){
        return Driver.builder()
                .name(dto.getName())
                .licenseNumber(dto.getLicenseNumber())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .driverStatus(dto.getStatus())
                .build();
    }
    public static Driver fromUpdate (UpdateDriverDTO dto){
        return Driver.builder()
                .id(dto.getDriverId())
                .name(dto.getName())
                .licenseNumber(dto.getLicenseNumber())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .driverStatus(dto.getStatus())
                .build();
    }
    public static UpdateDriverDTO toUpdateDTO (Driver driver){
        return UpdateDriverDTO.builder()
                .driverId(driver.getId())
                .name(driver.getName())
                .licenseNumber(driver.getLicenseNumber())
                .phone(driver.getPhone())
                .address(driver.getAddress())
                .status(driver.getDriverStatus())
                .build();
    }
    public static ResponseDriverDTO toResponseDTO (ArrayList<Driver> drivers){
        return ResponseDriverDTO.builder()
                .driverCount(drivers.size())
                .listDriver(drivers)
                .build();
    }
}
