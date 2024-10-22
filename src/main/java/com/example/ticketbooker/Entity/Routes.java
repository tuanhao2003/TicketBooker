package com.example.ticketbooker.Entity;

import com.example.ticketbooker.Util.Enum.RouteStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
@Builder
@Table(name = "routes")
public class Routes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routeId", nullable = false)
    private Integer id;

    @Column(name = "departureLocation", nullable = false, length = 100)
    private String departureLocation;

    @Column(name = "arrivalLocation", nullable = false, length = 100)
    private String arrivalLocation;

    @Column(name = "estimatedTime")
    private LocalTime estimatedTime;

    @Column(name = "routeStatus", nullable = false)
    @Enumerated(EnumType.STRING)
    private RouteStatus routeStatus;

    public Routes() {
        this.id = null;
        this.departureLocation = "";
        this.arrivalLocation = "";
        this.estimatedTime = null;
        this.routeStatus = RouteStatus.INACTIVE;
    }

    public Routes(Integer id, String departureLocation, String arrivalLocation, LocalTime estimatedTime, RouteStatus status) {
        this.id = id;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.estimatedTime = estimatedTime;
        this.routeStatus = status;
    }
}