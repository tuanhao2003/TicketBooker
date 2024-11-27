package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Trips.*;
import com.example.ticketbooker.Entity.Trips;

public interface TripService {
    public Trips getTrip(int id);
//    public ArrayList<Trips> findAll();
    public ResponseTripDTO getAllTrips();
    public boolean addTrip(AddTripDTO dto);
    public boolean updateTrip(UpdateTripDTO updateTripDTO);
    public boolean deleteTrip(RequestIdTripDTO dto); // Thêm phương thức xóa chuyến xe
    public ResponseTripDTO searchTrip(SearchTripRequest dto);
}
