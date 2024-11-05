package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Driver.AddDriverDTO;
import com.example.ticketbooker.DTO.Driver.ResponseDriverDTO;
import com.example.ticketbooker.DTO.Driver.UpdateDriverDTO;
import com.example.ticketbooker.Entity.Driver;
import com.example.ticketbooker.Repository.DriverRepo;
import com.example.ticketbooker.Service.DriverService;
import com.example.ticketbooker.Util.Enum.DriverStatus;
import com.example.ticketbooker.Util.Mapper.DriverMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

@Service
public class DriverServiceImp implements DriverService {
    @Autowired
    private DriverRepo driverRepo;

    @Override
    public boolean addDriver(AddDriverDTO dto) {
        try{
            Driver driver = DriverMapper.fromAdd(dto);
            this.driverRepo.save(driver);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateDriver(UpdateDriverDTO dto) {
        try{
            Driver driver = DriverMapper.fromUpdate(dto);
            this.driverRepo.save(driver);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteDriver(Integer id) {
        try{
            this.driverRepo.deleteById(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Driver getDriver(Integer id) {
        Driver result;
        try{
            result = driverRepo.findById(id).orElse(null);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }

    @Override
    public ResponseDriverDTO findAll() {
        ResponseDriverDTO response;
        try{
            ArrayList<Driver> drivers = this.driverRepo.findAll();
            response = DriverMapper.toResponseDTO(drivers);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return response;
    }

    @Override
    public ResponseDriverDTO findAllField(String searchTerm) {
        ResponseDriverDTO response;
        try{
            response = DriverMapper.toResponseDTO(this.driverRepo.searchDrivers(searchTerm));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return response;
    }

    @Override
    public ResponseDriverDTO findDriverByName(String driverName) {
        ResponseDriverDTO response;
        try{
            response = DriverMapper.toResponseDTO( this.driverRepo.findAllDriversByName(driverName));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return response;
    }

    @Override
    public ResponseDriverDTO findDriverByStatus(DriverStatus status) {
        ResponseDriverDTO response;
        try{
            response = DriverMapper.toResponseDTO(this.driverRepo.findAllDriversByDriverStatus(status));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return response;
    }

    @Override
    public ResponseDriverDTO findDriverByPhone(String phone) {
        return null;
    }
}
