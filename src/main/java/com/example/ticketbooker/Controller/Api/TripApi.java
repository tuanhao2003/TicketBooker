package com.example.ticketbooker.Controller.Api;

import com.example.ticketbooker.DTO.Trips.RequestIdTripDTO;
import com.example.ticketbooker.DTO.Trips.ResponseTripByIdDTO;
import com.example.ticketbooker.DTO.Trips.ResponseTripDTO;
import com.example.ticketbooker.DTO.Trips.SearchTripRequest;
import com.example.ticketbooker.Entity.Routes;
import com.example.ticketbooker.Service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.ticketbooker.Entity.Trips;

import java.time.format.DateTimeFormatter;
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

    @PostMapping("/search-trip")
    public ResponseTripDTO searchTrip(@RequestBody SearchTripRequest request, Model model) {
        ResponseTripDTO result = new ResponseTripDTO();
        try {
            result = tripService.searchTrip(request);
            model.addAttribute("responseTripDTO", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/{tripId}")
    public ResponseTripByIdDTO getTripById(@PathVariable int tripId) {
        try {
            // Tìm chuyến xe bằng tripId
            Trips trip = tripService.getTripByIdpath(tripId);
            if (trip == null) {
                return null; // Không tìm thấy chuyến xe
            }

            // Lấy thông tin tuyến xe từ đối tượng trip
            Routes route = trip.getRoute();

            // Tạo DTO trả về với thông tin cần thiết
            ResponseTripByIdDTO response = new ResponseTripByIdDTO();
            response.setDepartureLocation(route.getDepartureLocation()); // Lấy departureLocation từ Route
            response.setArrivalLocation(route.getArrivalLocation()); // Lấy arrivalLocation từ Route
            response.setDepartureTime(trip.getDepartureTime().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"))); // Định dạng thời gian xuất bến
            response.setTotalPrice(String.valueOf(trip.getPrice()));

            // Trả về đối tượng DTO chứa thông tin chuyến xe
            return response;

        } catch (Exception e) {
            e.printStackTrace();
            return null; // Lỗi trong quá trình xử lý
        }
    }
}
