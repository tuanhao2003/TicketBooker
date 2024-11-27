package com.example.ticketbooker.DTO.Trips;

import com.example.ticketbooker.Entity.Buses;
import com.example.ticketbooker.Entity.Driver;
import com.example.ticketbooker.Entity.Routes;
import com.example.ticketbooker.Util.Enum.BusStatus;
import com.example.ticketbooker.Util.Enum.BusType;
import com.example.ticketbooker.Util.Enum.TripStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripDTO {
    private Integer id;
    private Routes route;
    private Buses bus;
    private Driver driver;
    private String departureStation;
    private String arrivalStation;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer price;
    private Integer availableSeats;
    private TripStatus tripStatus;
}
