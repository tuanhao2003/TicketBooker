package com.example.ticketbooker.Controller;

import com.example.ticketbooker.Service.DriverService;
import com.example.ticketbooker.DTO.Driver.AddDriverDTO;
import com.example.ticketbooker.Entity.Driver;
import com.example.ticketbooker.Util.Enum.DriverStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin/driverManagement")
public class DriverController {
    @Autowired
    private DriverService driverService;
    @GetMapping
    public String profile(Model model) {
        ArrayList<Driver> drivers = driverService.findAll();
        model.addAttribute("drivers", drivers);
        return "View/User/AllDrivers";
    }
    @PostMapping ("/create")
    public String CreateDriver(@ModelAttribute ("driverAddForm") AddDriverDTO dto, Model model) {
        try {
            boolean result = driverService.addDriver(dto);
            if (result) {
                model.addAttribute("successMessage", "Successfully created");
            } else {
                model.addAttribute("failedMessage", "User create has failed");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/driverManagement";
    }
    @PostMapping ("/update")
    public String UpdateDriver(@ModelAttribute ("driverUpdateForm") AddDriverDTO dto, Model model) {
        try {
            boolean result = driverService.addDriver(dto);
            if (result) {
                model.addAttribute("successMessage", "Successfully created");
            } else {
                model.addAttribute("failedMessage", "User create has failed");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/driverManagement";
    }

    @GetMapping(params = "name")
    public String DriversFilterByName(Model model,@RequestParam String name) {
        ArrayList<Driver> drivers = driverService.findDriverByName(name);
        model.addAttribute("drivers", drivers);
        return "View/User/AllDrivers";
    }
//    @GetMapping(value = "/driver/all", params = "status")
//    public String DriversFilterByStatus(Model model,@RequestParam String status) {
//        DriverStatus statusEnum = DriverStatus.valueOf(status);
//        ArrayList<Driver> drivers = driverService.findDriverByStatus(statusEnum);
//        model.addAttribute("drivers", drivers);
//        return "View/User/AllDrivers";
//    }
}
