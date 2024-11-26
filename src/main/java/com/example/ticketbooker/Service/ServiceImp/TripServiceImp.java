package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Trips.AddTripDTO;
import com.example.ticketbooker.DTO.Trips.RequestIdTripDTO;
import com.example.ticketbooker.DTO.Trips.ResponseTripDTO;
import com.example.ticketbooker.DTO.Trips.UpdateTripDTO;
import com.example.ticketbooker.Entity.Trips;
import com.example.ticketbooker.Repository.TripRepo;
import com.example.ticketbooker.Service.TripService;
import com.example.ticketbooker.Util.Mapper.TripMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TripServiceImp implements TripService {
    @Autowired
    private TripRepo tripRepo;

    @Override
    public ResponseTripDTO getAllTrips() {
        ResponseTripDTO result = new ResponseTripDTO();
        try {
            result = TripMapper.toResponseDTO(this.tripRepo.findAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }

    @Override
    public boolean addTrip(AddTripDTO dto) {
        try {
            Trips trip = TripMapper.fromAdd(dto);
            this.tripRepo.save(trip);
        } catch (Exception e) {
            System.out.println("Error adding trip: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateTrip(UpdateTripDTO updateTripDTO) {
        try {
            Trips existingTrip = tripRepo.findById(updateTripDTO.getTripId()).orElse(null);
            if (existingTrip != null) {
                Trips updatedTrip = TripMapper.fromUpdate(updateTripDTO);
                tripRepo.save(updatedTrip);
                return true; // Cập nhật thành công
            }
            return false; // Không tìm thấy chuyến đi
        } catch (Exception e) {
            System.out.println("Error updating trip: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteTrip(RequestIdTripDTO dto) {
        try {
            this.tripRepo.deleteById(dto.getTripId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
