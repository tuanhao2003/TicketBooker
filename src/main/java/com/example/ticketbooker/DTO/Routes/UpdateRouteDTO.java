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
        this.departureLocation = "";
        this.arrivalLocation = "";
        this.estimatedTime = null;
        this.status = null;
    }

    public UpdateRouteDTO(int routeId, String departureLocation, String arrivalLocation, String estimatedTime, RouteStatus status) {
        this.routeId = routeId;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.estimatedTime = LocalTime.parse(estimatedTime);
        this.status = status;
    }
}
