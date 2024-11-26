package com.example.ticketbooker.Controller.Api;

import com.example.ticketbooker.DTO.Driver.RequestDriverIdDTO;
import com.example.ticketbooker.DTO.Driver.ResponseDriverDTO;
import com.example.ticketbooker.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/drivers")
public class DriverApi {
    @Autowired
    private DriverService driverService;
    @DeleteMapping("/delete")
    public boolean delete (@RequestBody RequestDriverIdDTO dto){
        boolean result;
        try{
            result = driverService.deleteDriver(dto.getDriverId());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return result;
    }
    @PostMapping("/search")
    public ResponseDriverDTO searchUser(@RequestBody String searchTerm) {
        ResponseDriverDTO response;
        try {
            response = this.driverService.findAllField(searchTerm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return response;
    }

    @GetMapping("/getAll")
    public ResponseDriverDTO getAllDrivers() {
        ResponseDriverDTO drivers;
        try{
            drivers = this.driverService.findAll();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return drivers;
    }
}
