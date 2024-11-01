package com.example.ticketbooker.DTO.Trips;

import com.example.ticketbooker.Entity.Buses;
import com.example.ticketbooker.Entity.Driver;
import com.example.ticketbooker.Entity.Routes;
import com.example.ticketbooker.Util.Enum.TripStatus;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AddTripDTO {
    private Routes route;
    private Buses bus;
    private Driver driver;
    private String departureStation;
    private String arrivalStation;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer price;
    private Integer availableSeats;
    private TripStatus tripStatus; // Có thể thêm trạng thái nếu cần

    public AddTripDTO() {
        this.route = null;
        this.bus = null;
        this.driver = null;
        this.departureStation = "";
        this.arrivalStation = "";
        this.departureTime = LocalDateTime.now();
        this.arrivalTime = null;
        this.price = null;
        this.availableSeats = null;
        this.tripStatus = TripStatus.SCHEDULED; // Trạng thái mặc định
    }
    public AddTripDTO(Routes routeId, Buses busId, Driver driverId, String departureStation, String arrivalStation, LocalDateTime departureTime, LocalDateTime arrivalTime, Integer price, Integer availableSeats, TripStatus tripStatus) {
        this.route = routeId;
        this.bus = busId;
        this.driver = driverId;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.availableSeats = availableSeats;
        this.tripStatus = tripStatus;
    }
}
