package com.example.ticketbooker.Repository;

import com.example.ticketbooker.Entity.Seats;
import com.example.ticketbooker.Util.Enum.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SeatsRepo extends JpaRepository<Seats, Integer> {
    ArrayList<Seats> findByTripIdAndSeatStatus(Integer tripId, SeatStatus seatStatus);  // Lấy chỗ còn trống theo chuyến
}
