package com.example.ticketbooker.DTO.Trips;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchTripRequest {
    private String departure;
    private String arrival;
    private Date departureDate;
    private Date arrivalDate;
    private int ticketQuantity;
}
