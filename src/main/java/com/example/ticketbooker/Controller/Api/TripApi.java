package com.example.ticketbooker.Controller.Api;

import com.example.ticketbooker.DTO.Trips.RequestIdTripDTO;
import com.example.ticketbooker.DTO.Trips.ResponseTripDTO;
import com.example.ticketbooker.Entity.Trips;
import com.example.ticketbooker.Service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/admin/trips")
public class TripApi {
    @Autowired
    private TripService tripService;

    @DeleteMapping
    public boolean deleteTrip(@RequestBody RequestIdTripDTO tripId) {
        boolean result = false;
        try {
            result = tripService.deleteTrip(tripId);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @GetMapping("/{tripId}")
    public ArrayList<Trips> getTripById(@PathVariable int tripId) {
        try {
            ResponseTripDTO trip = tripService.getTripById(tripId);
            System.out.println("Trip: " + trip);
            if (trip != null) {
                return trip.getListTrips();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
