package com.example.ticketbooker.Entity;

import com.example.ticketbooker.Util.Enum.DriverStatus;
import com.example.ticketbooker.Util.Enum.RouteStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import java.time.LocalTime;

@Data
@Builder
@Entity
@Table(name = "routes")
public class Routes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int routeId;

    @Column(name = "departureLocation", nullable = false)
    private String departureLocation;

    @Column(name = "arrivalLocation", nullable = false)
    private String arrivalLocation;

    @Column(name = "estimatedTime", nullable = true)
    private LocalTime estimatedTime;


    @Enumerated(EnumType.STRING)
    @Column(name = "routeStatus", nullable = true)
    private RouteStatus status;

    public Routes() {
        this.routeId = 0;
        this.departureLocation = "";
        this.arrivalLocation = "";
        this.estimatedTime = null;
        this.status = RouteStatus.ACTIVE;
    }

    public Routes(int routeId, String departureLocation, String arrivalLocation, LocalTime estimatedTime, RouteStatus status) {
        this.routeId = routeId;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.estimatedTime = estimatedTime;
        this.status = status;
    }

    public Integer getId() {
        return routeId;
    }
}

