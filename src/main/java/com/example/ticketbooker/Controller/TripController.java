package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Driver.ResponseDriverDTO;
import com.example.ticketbooker.DTO.Trips.AddTripDTO; // DTO để thêm chuyến xe
import com.example.ticketbooker.DTO.Trips.ResponseTripDTO;
import com.example.ticketbooker.DTO.Trips.UpdateTripDTO; // DTO để cập nhật chuyến xe
import com.example.ticketbooker.Entity.Driver;
import com.example.ticketbooker.Entity.Trips;
import com.example.ticketbooker.Service.DriverService;
import com.example.ticketbooker.Service.RouteService;
import com.example.ticketbooker.Service.TripService;
import com.example.ticketbooker.Util.Enum.TripStatus;
import com.example.ticketbooker.Util.Enum.UserStatus;
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
    @Autowired
    private RouteService routeService;
    @Autowired
    private DriverService driverService;


    @GetMapping
    public String tripManagement(Model model) {
        // Lấy danh sách các chuyến đi và thêm vào model
        ResponseTripDTO responseTripDTO = tripService.getAllTrips();
        model.addAttribute("listTrips", responseTripDTO);

        // In ra thông tin của listTrips
//        System.out.println("Số chuyến đi: " + responseTripDTO.getTripsCount());
//        for (Trips trip : responseTripDTO.getListTrips()) {
//            System.out.println("Trip ID: " + trip.getId());
//            System.out.println("Route ID: " + trip.getRoute().getRouteId());
//            System.out.println("Thành phố khởi hành: " + trip.getRoute().getDepartureLocation());
//            System.out.println("Thành phố khởi đến: " + trip.getRoute().getArrivalLocation());
//            System.out.println("Bus ID: " + trip.getBus().getId());
//            System.out.println("Bus license: " + trip.getBus().getLicensePlate());
//            System.out.println("Driver ID: " + trip.getDriver().getId());
//            System.out.println("Driver Name: " + trip.getDriver().getName());
//            System.out.println("Ga khởi hành: " + trip.getDepartureStation());
//            System.out.println("Ga đến: " + trip.getArrivalStation());
//            System.out.println("Thời gian khởi hành: " + trip.getDepartureTime());
//            System.out.println("Thời gian đến: " + trip.getArrivalTime());
//            System.out.println("Giá: " + trip.getPrice());
//            System.out.println("Chỗ trống: " + trip.getAvailableSeats());
//            System.out.println("Trạng thái: " + trip.getTripStatus());
//            System.out.println("--------------------");
//        }

        // Tạo các form để thêm và cập nhật chuyến đi
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
