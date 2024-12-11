package com.example.ticketbooker.Util.Mapper;


import com.example.ticketbooker.DTO.Bus.BusDTO;
import com.example.ticketbooker.DTO.Trips.AddTripDTO;
import com.example.ticketbooker.DTO.Trips.ResponseTripDTO;
import com.example.ticketbooker.DTO.Trips.TripDTO;
import com.example.ticketbooker.DTO.Trips.UpdateTripDTO;
import com.example.ticketbooker.DTO.Users.UpdateUserRequest;
import com.example.ticketbooker.Entity.Buses;
import com.example.ticketbooker.Entity.Trips;
import com.example.ticketbooker.Entity.Users;
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
                .tripsCount(trips.size())
                .build();
    }

    public static TripDTO toDTO(Trips trip) {
        if (trip == null) return null;

        TripDTO tripDTO = new TripDTO();
        tripDTO.setId(trip.getId());
        tripDTO.setRoute(trip.getRoute());
        tripDTO.setBus(trip.getBus());
        tripDTO.setDriver(trip.getDriver());
        tripDTO.setDepartureStation(trip.getDepartureStation());
        tripDTO.setArrivalStation(trip.getArrivalStation());
        tripDTO.setDepartureTime(trip.getDepartureTime());
        tripDTO.setArrivalTime(trip.getArrivalTime());
        tripDTO.setPrice(trip.getPrice());
        tripDTO.setAvailableSeats(trip.getAvailableSeats());
        tripDTO.setTripStatus(trip.getTripStatus());
        return tripDTO;
    }

    public static UpdateTripDTO toUpdateDTO(Trips entity) {
        return UpdateTripDTO.builder()
                .tripId(entity.getId())
                .route(entity.getRoute())
                .bus(entity.getBus())
                .driver(entity.getDriver())
                .departureStation(entity.getDepartureStation())
                .arrivalStation(entity.getArrivalStation())
                .departureTime(entity.getDepartureTime())
                .arrivalTime(entity.getArrivalTime())
                .price(entity.getPrice())
                .availableSeats(entity.getAvailableSeats())
                .tripStatus(entity.getTripStatus())
                .build();
    }
}
