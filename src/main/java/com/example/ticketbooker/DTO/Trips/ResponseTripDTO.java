package com.example.ticketbooker.DTO.Trips;

import com.example.ticketbooker.Entity.Trips;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class ResponseTripDTO {
    private ArrayList<Trips> listTrips;
    private int tripsCount;

    // Constructor không tham số
    public ResponseTripDTO() {
        this.tripsCount = 0;
        this.listTrips = new ArrayList<>();
    }

    // Constructor với các tham số
    public ResponseTripDTO(ArrayList<Trips> listTrips, int tripsCount) {
        this.listTrips = listTrips;
        this.tripsCount = tripsCount;
    }
}
