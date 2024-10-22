package com.example.ticketbooker.Util.Mapper;

import com.example.ticketbooker.DTO.Driver.AddDriverDTO;
import com.example.ticketbooker.Entity.Driver;

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
    public static Driver fromUpdate (int id, AddDriverDTO dto){
        return Driver.builder()
                .id(id)
                .name(dto.getName())
                .licenseNumber(dto.getLicenseNumber())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .driverStatus(dto.getStatus())
                .build();
    }
}
