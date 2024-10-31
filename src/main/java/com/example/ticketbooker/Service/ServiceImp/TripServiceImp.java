package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Trips.AddTripDTO;
import com.example.ticketbooker.DTO.Trips.RequestIdTripDTO;
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
    public Trips getTrip(int id) {
        Trips trips = null;
        try {
            trips = this.tripRepo.findById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return trips;
    }

    @Override
    public ArrayList<Trips> findAll() {
        ArrayList<Trips> trips = new ArrayList<>();
        try {
            trips = (ArrayList<Trips>) this.tripRepo.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return trips;
    }

    @Override
    public boolean addTrip(AddTripDTO addTripDTO) {
        try {
            Trips trip = TripMapper.fromAdd(addTripDTO);
            tripRepo.save(trip);
            return true;
        } catch (Exception e) {
            System.out.println("Error adding trip: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateTrip(UpdateTripDTO updateTripDTO) {
        try {
            // Tìm chuyến đi hiện tại theo ID
            Trips existingTrip = tripRepo.findById(updateTripDTO.getTripId()).orElse(null);
            if (existingTrip != null) {
                // Chuyển đổi DTO thành Entity
                Trips updatedTrip = TripMapper.fromUpdate(existingTrip.getTripId(), updateTripDTO);
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
