package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Driver.AddDriverDTO;
import com.example.ticketbooker.DTO.Driver.ResponseDriverDTO;
import com.example.ticketbooker.DTO.Driver.UpdateDriverDTO;
import com.example.ticketbooker.Entity.Driver;
import com.example.ticketbooker.Util.Enum.DriverStatus;

import java.util.ArrayList;

public interface DriverService {
    public boolean addDriver(AddDriverDTO dto);
    public boolean updateDriver(UpdateDriverDTO dto);
    public boolean deleteDriver(int id);
    public ResponseDriverDTO getDriver(int id);
    public ResponseDriverDTO findAll();
    public ResponseDriverDTO findDriverByName(String name);
    public ResponseDriverDTO findDriverByStatus(DriverStatus status);
    public ResponseDriverDTO findDriverByPhone(String phone);
}
