package com.example.ticketbooker.Entity;

import com.example.ticketbooker.Util.Enum.BusStatus;
import com.example.ticketbooker.Util.Enum.BusType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "buses")
public class Buses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "busId", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "routeId")
    private Routes route;

    @Column(name = "licensePlate", nullable = false, length = 20)
    private String licensePlate;


    @Column(name = "busType", nullable = false)
    @Enumerated(EnumType.STRING)
    private BusType busType;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "busStatus", nullable = false)
    @Enumerated(EnumType.STRING)
    private BusStatus busStatus;

    public Buses() {
        this.id = null;
        this.route = null;
        this.licensePlate = "";
        this.busType = BusType.SEAT;
        this.capacity = 0;
        this.busStatus = BusStatus.ACTIVE;
    }

    public Buses(Integer id, Routes route, String licensePlate, BusType busType, Integer capacity, BusStatus busStatus) {
        this.id = id;
        this.route = route;
        this.licensePlate = licensePlate;
        this.busType = busType;
        this.capacity = capacity;
        this.busStatus = busStatus;
    }
}