package com.example.ticketbooker.DTO.Trips;

import com.example.ticketbooker.Util.Enum.TripStatus;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddTripDTO {
    private Integer routeId;
    private Integer busId;
    private Integer driverId;
    private String departureStation;
    private String arrivalStation;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer price;
    private Integer availableSeats;
    private TripStatus tripStatus; // Có thể thêm trạng thái nếu cần

    public AddTripDTO() {
        this.routeId = null;
        this.busId = null;
        this.driverId = null;
        this.departureStation = "";
        this.arrivalStation = "";
        this.departureTime = LocalDateTime.now();
        this.arrivalTime = null;
        this.price = null;
        this.availableSeats = null;
        this.tripStatus = TripStatus.Scheduled; // Trạng thái mặc định
    }
}
