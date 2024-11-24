package com.example.ticketbooker.Util.Mapper;


import com.example.ticketbooker.DTO.Trips.AddTripDTO;
import com.example.ticketbooker.DTO.Trips.ResponseTripDTO;
import com.example.ticketbooker.DTO.Trips.UpdateTripDTO;
import com.example.ticketbooker.Entity.Trips;
import com.example.ticketbooker.Util.Enum.TripStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TripMapper {
    public static Trips fromAdd(AddTripDTO dto) {
        return Trips.builder()
                .route(dto.getRoute())
                .bus(dto.getBus())
                .driver(dto.getDriver())
                .departureStation(dto.getDepartureStation())
                .arrivalStation(dto.getArrivalStation())
                .departureTime(LocalDateTime.from(dto.getDepartureTime()))
                .price(dto.getPrice())
                .availableSeats(dto.getAvailableSeats())
                .tripStatus(dto.getTripStatus() != null ? dto.getTripStatus() : TripStatus.SCHEDULED)
                .build();
    }

    public static Trips fromUpdate(UpdateTripDTO dto) {
        Trips.TripsBuilder tripBuilder = Trips.builder()
                .id(dto.getTripId())
                .route(dto.getRoute())
                .bus(dto.getBus())
                .driver(dto.getDriver())
                .departureStation(dto.getDepartureStation())
                .arrivalStation(dto.getArrivalStation())
                .departureTime(LocalDateTime.from(dto.getDepartureTime()))
                .price(dto.getPrice())
                .availableSeats(dto.getAvailableSeats())
                .tripStatus(dto.getTripStatus());

        if (dto.getArrivalTime() != null) {
            tripBuilder.arrivalTime(LocalDateTime.from(dto.getArrivalTime()));
        } else {
            tripBuilder.arrivalTime(null);
        }

        return tripBuilder.build();
    }

    public static ResponseTripDTO toResponseDTO(ArrayList<Trips> trips) {
        return ResponseTripDTO.builder()
                .listTrips(trips)
                .build();
    }
}
