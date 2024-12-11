package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Bus.BusDTO;
import com.example.ticketbooker.DTO.Trips.AddTripDTO;
import com.example.ticketbooker.DTO.Trips.ResponseTripDTO;
import com.example.ticketbooker.DTO.Trips.TripDTO;
import com.example.ticketbooker.DTO.Trips.UpdateTripDTO;
import com.example.ticketbooker.DTO.Users.UpdateUserRequest;
import com.example.ticketbooker.Entity.Trips;
import com.example.ticketbooker.Service.BusService;
import com.example.ticketbooker.Service.DriverService;
import com.example.ticketbooker.Service.RouteService;
import com.example.ticketbooker.Service.TripService;
import com.example.ticketbooker.Util.Enum.TripStatus;
import com.example.ticketbooker.Util.Mapper.TripMapper;
import com.example.ticketbooker.Util.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin/trips")
public class TripController {
    @Autowired
    private TripService tripService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private DriverService driverService;

    @Autowired
    private BusService busService;

//    @GetMapping
//    public String tripManagement(Model model) {
//        ResponseTripDTO responseTripDTO = tripService.getAllTrips();
//        model.addAttribute("listTrips", responseTripDTO);
//
//        model.addAttribute("createTripForm", new AddTripDTO());
//        model.addAttribute("updateTripForm", new UpdateTripDTO());
//
//        return "View/Admin/Trips/AllTrips";
//    }

    @GetMapping()
    public String listTrips(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<TripDTO> tripPage = tripService.getAllTrips(pageable);

        // Log danh sách TripDTO ra console
        System.out.println("Danh sách TripDTO:");
        tripPage.getContent().forEach(System.out::println);

        List<BusDTO> buses = busService.getAllBuses();
        model.addAttribute("buses", buses);

        model.addAttribute("trips", tripPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", tripPage.getTotalPages());
        model.addAttribute("totalElements", tripPage.getTotalElements());
        model.addAttribute("size", size);

        model.addAttribute("createTripForm", new AddTripDTO());
        model.addAttribute("updateTripForm", new UpdateTripDTO());
        return "View/Admin/Trips/AllTrips";
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
        return "redirect:/admin/trips";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("updateTripForm") UpdateTripDTO updateTripDTO, Model model) {
        try {
            System.out.println("Update trip: " + updateTripDTO);
            int id = updateTripDTO.getTripId();
            boolean result = tripService.updateTrip(updateTripDTO);
            if (result) {
                model.addAttribute("successMessage", "Successfully updated");
            } else {
                model.addAttribute("failedMessage", "Trip update has failed");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/trips/details/"+ updateTripDTO.getTripId();

    }

    @GetMapping("/details/{id}")
    public String tripDetails(@PathVariable int id, Model model) {
        UpdateTripDTO updateTripDTO = new UpdateTripDTO();
        if(tripService.getTripByIds(id).getTripsCount() == 1){
            updateTripDTO = TripMapper.toUpdateDTO(tripService.getTripByIds(id).getListTrips().get(0));
        }
        System.out.println(tripService.getTripByIds(id).getTripsCount());
        System.out.println(tripService.getTripByIds(id).getListTrips().get(0));
        System.out.println(updateTripDTO.getTripId());

        model.addAttribute("updateTripForm", updateTripDTO);
        return "View/Admin/Trips/TripDetail";
    }
}
