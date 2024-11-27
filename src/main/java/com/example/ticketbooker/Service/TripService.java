package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Trips.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TripService {
    public ResponseTripDTO getTripById(int id);
    Page<TripDTO> getAllTrips(Pageable pageable);
//    public ArrayList<Trips> findAll();
    public ResponseTripDTO getAllTrips();
    public boolean addTrip(AddTripDTO dto);
    public boolean updateTrip(UpdateTripDTO updateTripDTO);
    public boolean deleteTrip(RequestIdTripDTO dto); // Thêm phương thức xóa chuyến xe
    public ResponseTripDTO searchTrip(SearchTripRequest dto);
}
