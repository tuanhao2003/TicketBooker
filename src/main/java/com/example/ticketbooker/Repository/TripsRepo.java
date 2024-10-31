package com.example.ticketbooker.Repository;

import com.example.ticketbooker.Entity.Trips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripsRepo extends JpaRepository<Trips, Integer> {
    Trips findById(int tripId);  // Lấy thông tin chuyến đi theo ID
}
