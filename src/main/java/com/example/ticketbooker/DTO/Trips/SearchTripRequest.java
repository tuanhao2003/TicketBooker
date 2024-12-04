package com.example.ticketbooker.DTO.Trips;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchTripRequest {
    private String departure;
    private String arrival;
    private LocalDateTime departureDate;
    private int ticketQuantity;
}
