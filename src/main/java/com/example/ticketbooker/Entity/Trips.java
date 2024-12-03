package com.example.ticketbooker.Entity;

import com.example.ticketbooker.Util.Enum.TripStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "trips")
public class Trips {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tripId", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "routeId")
    private Routes route;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "busId")
    private Buses bus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "driverId")
    private Driver driver;

    @Column(name = "departureStation", length = 100, nullable = false)
    private String departureStation;

    @Column(name = "arrivalStation", length = 100, nullable = false)
    private String arrivalStation;

    @Column(name = "departureTime", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrivalTime")
    private LocalDateTime arrivalTime;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "availableSeats", nullable = false)
    private Integer availableSeats;

    @Column(name = "tripStatus", nullable = false)
    @Enumerated(EnumType.STRING)
    private TripStatus tripStatus;

    public Trips() {
        this.id = null;
        this.route = new Routes();
        this.bus = new Buses();
        this.driver = new Driver();
        this.departureStation = "";
        this.arrivalStation = "";
        this.departureTime = LocalDateTime.now();
        this.arrivalTime = null;
        this.price = 0;
        this.availableSeats = 0;
        this.tripStatus = TripStatus.SCHEDULED;
    }

    public Trips(Integer id, Routes route, Buses bus, Driver driver, String departureStation, String arrivalStation, LocalDateTime departureTime, LocalDateTime arrivalTime, Integer price, Integer availableSeats, TripStatus tripStatus) {
        this.id = id;
        this.route = route;
        this.bus = bus;
        this.driver = driver;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.availableSeats = availableSeats;
        this.tripStatus = tripStatus;
    }
}
