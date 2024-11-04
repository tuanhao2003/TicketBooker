package com.example.ticketbooker.DTO.Routes;

import com.example.ticketbooker.Util.Enum.RouteStatus;
import java.time.LocalTime;
import lombok.Builder;
import lombok.Data;

@Builder
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

    public UpdateRouteDTO(int routeId, String departureLocation, String arrivalLocation, LocalTime estimatedTime, RouteStatus status) {
        this.routeId = routeId;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.estimatedTime = estimatedTime;
        this.status = status;
    }
}
