package com.example.ticketbooker.DTO.Driver;

import com.example.ticketbooker.Util.Enum.DriverStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverDTO {
    private Integer id;
    private String name;
    private String licenseNumber;
    private String phone;
    private String address;
    private DriverStatus driverStatus;
}
