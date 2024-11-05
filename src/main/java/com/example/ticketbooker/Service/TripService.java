package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Trips.AddTripDTO;
import com.example.ticketbooker.DTO.Trips.RequestIdTripDTO;
import com.example.ticketbooker.DTO.Trips.ResponseTripDTO;
import com.example.ticketbooker.DTO.Trips.UpdateTripDTO;
import com.example.ticketbooker.DTO.Users.ResponseUserDTO;
import com.example.ticketbooker.Entity.Trips;

import java.util.ArrayList;

public interface TripService {
    public Trips getTrip(int id);
//    public ArrayList<Trips> findAll();
    public ResponseTripDTO getAllTrips();
    public boolean addTrip(AddTripDTO dto);
    public boolean updateTrip(UpdateTripDTO updateTripDTO);
    public boolean deleteTrip(RequestIdTripDTO dto); // Thêm phương thức xóa chuyến xe
}
