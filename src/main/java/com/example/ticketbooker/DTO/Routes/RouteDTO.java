package com.example.ticketbooker.DTO.Routes;

import com.example.ticketbooker.Util.Enum.RouteStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteDTO {
    private Integer routeId;
    private String departureLocation;
    private String arrivalLocation;
    private LocalTime estimatedTime;
    private RouteStatus status;
}
