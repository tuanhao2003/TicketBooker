package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Trips.AddTripDTO; // DTO để thêm chuyến xe
import com.example.ticketbooker.DTO.Trips.UpdateTripDTO; // DTO để cập nhật chuyến xe
import com.example.ticketbooker.Entity.Trips;
import com.example.ticketbooker.Service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin/tripManagement")
public class TripController {
    @Autowired
    private TripService tripService;

    @GetMapping
    public String tripManagement(Model model) {
        model.addAttribute("listTrips", tripService.findAll());
        model.addAttribute("createTripForm", new AddTripDTO());
        model.addAttribute("updateTripForm", new UpdateTripDTO());
        return "View/Admin/AllTrips";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("createTripForm") AddTripDTO addTripDTO, Model model) {
        try {
            if (addTripDTO.getDriverId() == null) {
                model.addAttribute("errorMessage", "Mã tài xế không được để trống.");
                return "redirect:/admin/tripManagement";
            }

            boolean result = tripService.addTrip(addTripDTO);
            if (result) {
                model.addAttribute("successMessage", "Successfully created");
            } else {
                model.addAttribute("failedMessage", "Trip create has failed");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/tripManagement/all";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("updateTripForm") UpdateTripDTO updateTripDTO, Model model) {
        try {
            boolean result = tripService.updateTrip(updateTripDTO);
            if (result) {
                model.addAttribute("successMessage", "Successfully updated");
            } else {
                model.addAttribute("failedMessage", "Trip update has failed");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/tripManagement/all";
    }

    @GetMapping("/trip")
    public String Trip(Model model, @RequestParam int id){
        Trips trip = tripService.getTrip(id);

        if (trip == null) {
            System.out.println("No trip found for ID: " + id);
            return "error";
        }
        model.addAttribute("trip", trip);
        return "View/Admin/Trip";
    }

    @GetMapping("/all")
    public String AllTrips(Model model) {
        ArrayList<Trips> trips = tripService.findAll();
        model.addAttribute("trips", trips);
        return "View/Admin/AllTrips";
    }
}
