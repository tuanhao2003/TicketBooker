package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Seats.AddSeatDTO;
import com.example.ticketbooker.Entity.Seats;

import java.util.List;

public interface SeatsService {
    List<Integer> addSeats(AddSeatDTO addSeatDTO);
    List<String> getBookedSeatsForTrip(Integer tripId);
    Seats getSeatById(int id);

    void deleteSeat(int i);
}
