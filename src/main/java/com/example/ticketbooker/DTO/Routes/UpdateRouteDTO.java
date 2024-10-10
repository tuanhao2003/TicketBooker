package com.example.ticketbooker.DTO.Routes;

import com.example.ticketbooker.Util.Enum.RouteStatus;
import lombok.Data;

import java.time.LocalTime;
@Data
public class UpdateRouteDTO {
    private int routeId;
    private String departureLocation;
    private String arrivalLocation;
    private LocalTime estimatedTime;
    private RouteStatus status;

    public UpdateRouteDTO() {
        this.routeId = 0;
        this.departureLocation = "";
        this.arrivalLocation = null;
        this.estimatedTime = null;
        this.status = RouteStatus.Active;
    }

    public UpdateRouteDTO(int routeId, String departureLocation, String arrivalLocation, String estimatedTime, RouteStatus status) {
        this.routeId = 0;
        this.departureLocation = "";
        this.arrivalLocation = null;
        this.estimatedTime = null;
        this.status = status;
    }
}
