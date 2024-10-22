package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Driver.AddDriverDTO;
import com.example.ticketbooker.Entity.Driver;
import com.example.ticketbooker.Util.Enum.DriverStatus;

import java.util.ArrayList;

public interface DriverService {
    public boolean addDriver(AddDriverDTO dto);
    public boolean updateDriver(int id, AddDriverDTO dto);
    public boolean deleteDriver(int id);
    public Driver getDriver(int id);
    public ArrayList<Driver> findAll();
    public ArrayList<Driver> findDriverByName(String name);
    public ArrayList<Driver> findDriverByStatus(DriverStatus status);
}
