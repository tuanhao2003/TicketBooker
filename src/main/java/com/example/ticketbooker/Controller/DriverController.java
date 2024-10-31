package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Driver.ResponseDriverDTO;
import com.example.ticketbooker.DTO.Driver.UpdateDriverDTO;
import com.example.ticketbooker.DTO.Users.UpdateUserDTO;
import com.example.ticketbooker.Service.DriverService;
import com.example.ticketbooker.DTO.Driver.AddDriverDTO;
import com.example.ticketbooker.Entity.Driver;
import com.example.ticketbooker.Util.Enum.DriverStatus;
import com.example.ticketbooker.Util.Mapper.DriverMapper;
import com.example.ticketbooker.Util.Mapper.UserMapper;
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
        ResponseDriverDTO response = driverService.findAll();
        model.addAttribute("response", response);
        model.addAttribute("createDriverForm", new AddDriverDTO());
        model.addAttribute("updateDriverForm", new UpdateDriverDTO());
        return "View/Admin/DriverManagement";
    }
    @PostMapping ("/create")
    public String CreateDriver(@ModelAttribute ("createDriverForm") AddDriverDTO dto, Model model) {
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
    public String UpdateDriver(@ModelAttribute ("updateDriverForm") UpdateDriverDTO dto, Model model) {
        try {
            boolean result = driverService.updateDriver(dto);
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

    @GetMapping("/details/{id}")
    public String driverDetails(@PathVariable int id, Model model) {
        UpdateDriverDTO updateDriverDTO = new UpdateDriverDTO();
        if(driverService.getDriver(id).getDriverCount() == 1){
            updateDriverDTO = DriverMapper.toUpdateDTO(driverService.getDriver(id).getListDriver().get(0));
        }
        model.addAttribute("updateDriverForm", updateDriverDTO);
        return "View/Admin/DriverManagement/DriverDetails";
    }

    @GetMapping(params = "name")
    public String DriversFilterByName(Model model,@RequestParam String name) {
        ResponseDriverDTO response = driverService.findDriverByName(name);
        model.addAttribute("response", response);
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
