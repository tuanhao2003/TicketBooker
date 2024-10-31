package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Trips.AddTripDTO;
import com.example.ticketbooker.DTO.Trips.RequestIdTripDTO;
import com.example.ticketbooker.DTO.Trips.UpdateTripDTO;
import com.example.ticketbooker.Entity.Trips;

import java.util.ArrayList;

public interface TripService {
    public Trips getTrip(int id);
    public ArrayList<Trips> findAll();
    public boolean addTrip(AddTripDTO addTripDTO);
    public boolean updateTrip(UpdateTripDTO updateTripDTO);
    public boolean deleteTrip(RequestIdTripDTO dto); // Thêm phương thức xóa chuyến xe
}
