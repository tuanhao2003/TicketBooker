package com.example.ticketbooker.DTO.Routes;

import com.example.ticketbooker.Util.Enum.RouteStatus;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalTime;
@Data
public class AddRouteDTO {
    private int routeId;
    private String departureLocation;
    private String arrivalLocation;
    private LocalTime estimatedTime;
    private RouteStatus status;

    public AddRouteDTO() {
        this.routeId = 0;
        this.departureLocation = "";
        this.arrivalLocation = null;
        this.estimatedTime = null;
        this.status = RouteStatus.ACTIVE;
    }

    public AddRouteDTO(int routeId, String departureLocation, String arrivalLocation, String estimatedTime, RouteStatus status) {
        this.routeId = 0;
        this.departureLocation = "";
        this.arrivalLocation = null;
        this.estimatedTime = null;
        this.status = status;
    }
}
