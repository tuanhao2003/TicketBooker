package com.example.ticketbooker.DTO.Bus;

import com.example.ticketbooker.Util.Enum.BusStatus;
import com.example.ticketbooker.Util.Enum.BusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BusDTO {
    private Integer id;
    private Integer routeId;
    private String licensePlate;
    private BusType busType;
    private Integer capacity;
    private BusStatus busStatus;

    public BusDTO() {
        this.id = null;
        this.routeId = null;
        this.licensePlate = null;
        this.busType = null;
        this.capacity = null;
        this.busStatus = null;
    }

    public BusDTO(Integer id, Integer routeId, String licensePlate, BusType busType, Integer capacity, BusStatus busStatus) {
        this.id = id;
        this.routeId = routeId;
        this.licensePlate = licensePlate;
        this.busType = busType;
        this.capacity = capacity;
        this.busStatus = busStatus;
    }
}
