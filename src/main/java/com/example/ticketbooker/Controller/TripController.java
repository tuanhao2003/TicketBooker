package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Trips.AddTripDTO;
import com.example.ticketbooker.DTO.Trips.ResponseTripDTO;
import com.example.ticketbooker.DTO.Trips.UpdateTripDTO;
import com.example.ticketbooker.Entity.Trips;
import com.example.ticketbooker.Service.DriverService;
import com.example.ticketbooker.Service.RouteService;
import com.example.ticketbooker.Service.TripService;
import com.example.ticketbooker.Util.Enum.TripStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin/tripManagement")
public class TripController {
    @Autowired
    private TripService tripService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private DriverService driverService;


    @GetMapping
    public String tripManagement(Model model) {
        ResponseTripDTO responseTripDTO = tripService.getAllTrips();
        model.addAttribute("listTrips", responseTripDTO);
        
        model.addAttribute("createTripForm", new AddTripDTO());
        model.addAttribute("updateTripForm", new UpdateTripDTO());

        return "View/Admin/TripManagement/AllTrips";
    }


    @PostMapping("/create")
    public String createTrip(@ModelAttribute("createTripForm") AddTripDTO addTripDTO, Model model) {
        try {
            addTripDTO.setTripStatus(TripStatus.SCHEDULED);
            System.out.println("Creating trip: " + addTripDTO);

            boolean result = tripService.addTrip(addTripDTO);
            if (result) {
                model.addAttribute("successMessage", "Successfully created");
            } else {
                model.addAttribute("failedMessage", "Trip creation has failed");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error adding trip: " + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/admin/tripManagement";
    }



    @PostMapping("/update")
    public String update(@ModelAttribute("updateTripForm") UpdateTripDTO updateTripDTO, Model model) {
        try {
            System.out.println("Update trip: " + updateTripDTO);
            boolean result = tripService.updateTrip(updateTripDTO);
            if (result) {
                model.addAttribute("successMessage", "Successfully updated");
            } else {
                model.addAttribute("failedMessage", "Trip update has failed");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/tripManagement";
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
}
