package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Driver.AddDriverDTO;
import com.example.ticketbooker.Entity.Driver;
import com.example.ticketbooker.Repository.DriverRepo;
import com.example.ticketbooker.Service.DriverService;
import com.example.ticketbooker.Util.Enum.DriverStatus;
import com.example.ticketbooker.Util.Mapper.DriverMapper;
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
    public boolean updateDriver(int id, AddDriverDTO dto) {
        try{
            Driver driver = DriverMapper.fromUpdate(id,dto);
            this.driverRepo.save(driver);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteDriver(int id) {
        try{
            this.driverRepo.deleteById(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Driver getDriver(int id) {
        Driver driver = null;
        try{
            driver = this.driverRepo.findById(id);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return driver;
    }

    @Override
    public ArrayList<Driver> findAll() {
        ArrayList<Driver> drivers;
        try{
            drivers = this.driverRepo.findAll();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return drivers;
    }

    @Override
    public ArrayList<Driver> findDriverByName(String driverName) {
        ArrayList<Driver> drivers;
        try{
            drivers = this.driverRepo.findDriverByName(driverName);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return drivers;
    }

    @Override
    public ArrayList<Driver> findDriverByStatus(DriverStatus status) {
        ArrayList<Driver> drivers;
        try{
            drivers = this.driverRepo.findDriversByDriverStatus(status);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return drivers;
    }
}
