package com.example.ticketbooker.Controller.Api;

import com.example.ticketbooker.DTO.Trips.RequestIdTripDTO;
import com.example.ticketbooker.Service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
