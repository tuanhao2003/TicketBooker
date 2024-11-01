package com.example.ticketbooker.Util.Mapper;


import com.example.ticketbooker.DTO.Trips.AddTripDTO;
import com.example.ticketbooker.DTO.Trips.RequestIdTripDTO;
import com.example.ticketbooker.DTO.Trips.UpdateTripDTO;
import com.example.ticketbooker.Entity.Trips;
import com.example.ticketbooker.Repository.TripRepo;
import com.example.ticketbooker.Service.TripService;
import com.example.ticketbooker.Util.Enum.TripStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;

public class TripMapper {
    public static Trips fromAdd(AddTripDTO dto) {

        return Trips.builder()
                .route(dto.getRoute())
                .bus(dto.getBus())
                .driver(dto.getDriver())
                .departureStation(dto.getDepartureStation())
                .arrivalStation(dto.getArrivalStation())
                .departureTime(Instant.from(dto.getDepartureTime()))
                .arrivalTime(Instant.from(dto.getArrivalTime()))
                .price(dto.getPrice())
                .availableSeats(dto.getAvailableSeats())
                .tripStatus(dto.getTripStatus() != null ? dto.getTripStatus() : TripStatus.SCHEDULED) // Sử dụng trạng thái mặc định
                .build();
    }

    public static Trips fromUpdate(UpdateTripDTO dto) {
        return Trips.builder()
                .id(dto.getTripId())
                .route(dto.getRoute())
                .bus(dto.getBus())
                .driver(dto.getDriver())
                .departureStation(dto.getDepartureStation())
                .arrivalStation(dto.getArrivalStation())
                .departureTime(Instant.from(dto.getDepartureTime()))
                .arrivalTime(Instant.from(dto.getArrivalTime()))
                .price(dto.getPrice())
                .availableSeats(dto.getAvailableSeats())
                .tripStatus(dto.getTripStatus()) // Cập nhật trạng thái nếu có
                .build();
    }
}
