package com.example.ticketbooker.Util.Mapper;

import com.example.ticketbooker.DTO.Seats.AddSeatDTO;
import com.example.ticketbooker.Entity.Seats;
import com.example.ticketbooker.Entity.Trips;
import org.springframework.stereotype.Component;

@Component
public class SeatsMapper {
    public Seats toEntity(AddSeatDTO addSeatDTO, Trips trip) {
        return Seats.builder()
                .trip(trip)
                .seatCode(addSeatDTO.getSeatCode())
                .build();
    }
}
