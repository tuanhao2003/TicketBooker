package com.example.ticketbooker.Util.Mapper;

import com.example.ticketbooker.DTO.Trips.AddTripDTO;
import com.example.ticketbooker.DTO.Trips.UpdateTripDTO;
import com.example.ticketbooker.Entity.Trips;
import com.example.ticketbooker.Util.Enum.TripStatus;
public class TripMapper {

    public static Trips fromAdd(AddTripDTO dto) {
        return Trips.builder()
                .routeId(dto.getRouteId())
                .busId(dto.getBusId())
                .driverId(dto.getDriverId())
                .departureStation(dto.getDepartureStation())
                .arrivalStation(dto.getArrivalStation())
                .departureTime(dto.getDepartureTime())
                .arrivalTime(dto.getArrivalTime())
                .price(dto.getPrice())
                .availableSeats(dto.getAvailableSeats())
                .tripStatus(dto.getTripStatus() != null ? dto.getTripStatus() : TripStatus.Scheduled) // Sử dụng trạng thái mặc định
                .build();
    }

    public static Trips fromUpdate(int id, UpdateTripDTO dto) {
        return Trips.builder()
                .tripId(id)
                .routeId(dto.getRouteId())
                .busId(dto.getBusId())
                .driverId(dto.getDriverId())
                .departureStation(dto.getDepartureStation())
                .arrivalStation(dto.getArrivalStation())
                .departureTime(dto.getDepartureTime())
                .arrivalTime(dto.getArrivalTime())
                .price(dto.getPrice())
                .availableSeats(dto.getAvailableSeats())
                .tripStatus(dto.getTripStatus()) // Cập nhật trạng thái nếu có
                .build();
    }
}
