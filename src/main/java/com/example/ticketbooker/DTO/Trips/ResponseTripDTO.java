package com.example.ticketbooker.DTO.Trips;

import com.example.ticketbooker.Entity.Trips;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTripDTO {
    private ArrayList<Trips> listTrips;
    private int tripsCount;

}
