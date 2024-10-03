package com.example.ticketbooker.Controller;

import com.example.ticketbooker.Service.DriverService;
import com.example.ticketbooker.Entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class DriverController {
    @Autowired
    private DriverService driverService;
    @GetMapping("/driver")
    public String Driver(Model model, @RequestParam int id) {
        Driver driver = driverService.getDriver(id);
        model.addAttribute("driver", driver);
        return "View/User/Driver";
    }
    @GetMapping("/driver/all")
    public String AllDrivers(Model model) {
        ArrayList<Driver> drivers = driverService.findAll();
        model.addAttribute("drivers", drivers);
        return "View/User/AllDrivers";
    }
}
