package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Driver.AddDriverDTO;
import com.example.ticketbooker.DTO.Driver.DriverDTO;
import com.example.ticketbooker.DTO.Driver.ResponseDriverDTO;
import com.example.ticketbooker.DTO.Driver.UpdateDriverDTO;
import com.example.ticketbooker.Entity.Driver;
import com.example.ticketbooker.Util.Enum.DriverStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface DriverService {
    boolean addDriver(AddDriverDTO dto);
    boolean updateDriver(UpdateDriverDTO dto);
    boolean deleteDriver(Integer id);
    Driver getDriver(Integer id);
    ResponseDriverDTO findAll();
    Page<DriverDTO> findAll(Pageable pageable);
    ResponseDriverDTO findAllField(String searchTerm);
    ResponseDriverDTO findDriverByName(String name);
    ResponseDriverDTO findDriverByStatus(DriverStatus status);
    ResponseDriverDTO findDriverByPhone(String phone);
}
