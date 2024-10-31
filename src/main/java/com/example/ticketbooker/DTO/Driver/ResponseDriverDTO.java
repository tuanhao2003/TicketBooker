package com.example.ticketbooker.DTO.Driver;

import com.example.ticketbooker.Entity.Driver;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class ResponseDriverDTO {
    private int driverCount;
    private ArrayList<Driver> listDriver;

    public ResponseDriverDTO() {
        this.driverCount = 0;
        this.listDriver = new ArrayList<>();
    }
    public ResponseDriverDTO(int driverCount, ArrayList<Driver> listDriver) {
        this.driverCount = driverCount;
        this.listDriver = listDriver;
    }
}
