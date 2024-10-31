package com.example.ticketbooker.DTO.Trips;

import lombok.Data;

@Data
public class RequestIdTripDTO {
    Integer tripId;
    public RequestIdTripDTO(Integer tripId){
        this.tripId = tripId;
    }

    public RequestIdTripDTO(){
        this.tripId = null;
    }
}
