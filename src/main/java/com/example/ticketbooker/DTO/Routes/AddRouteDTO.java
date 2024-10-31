package com.example.ticketbooker.DTO.Routes;

import com.example.ticketbooker.Util.Enum.RouteStatus;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalTime;
@Data
public class AddRouteDTO {
    private String departureLocation;
    private String arrivalLocation;
    private LocalTime estimatedTime;
    private RouteStatus status;

    public AddRouteDTO() {
        this.departureLocation = "";
        this.arrivalLocation = "";
        this.estimatedTime = null;
        this.status = null;
    }

    public AddRouteDTO(String departureLocation, String arrivalLocation, String estimatedTime, RouteStatus status) {
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.estimatedTime = LocalTime.parse(estimatedTime);
        this.status = status;
    }
}
