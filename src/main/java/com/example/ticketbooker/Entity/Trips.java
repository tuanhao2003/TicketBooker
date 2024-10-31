package com.example.ticketbooker.Entity;

import com.example.ticketbooker.Util.Enum.TripStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

<<<<<<< HEAD
import java.time.Instant;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
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
    private Instant departureTime;

    @Column(name = "arrivalTime")
    private Instant arrivalTime;

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
        this.departureTime = Instant.now();
        this.arrivalTime = null;
        this.price = 0;
        this.availableSeats = 0;
        this.tripStatus = TripStatus.SCHEDULED;
    }

    public Trips(Integer id, Routes route, Buses bus, Driver driver, String departureStation, String arrivalStation, Instant departureTime, Instant arrivalTime, Integer price, Integer availableSeats, TripStatus tripStatus) {
        this.id = id;
        this.route = route;
        this.bus = bus;
        this.driver = driver;
=======
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "trips")
public class Trips {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tripId;

    @Column(name = "routeId", nullable = true)
    private int routeId;

    @Column(name = "busId", nullable = true)
    private int busId;

    @Column(name = "driverId", nullable = true)
    private int driverId;

    @Column(name = "departureStation", nullable = true, length = 100)
    private String departureStation;

    @Column(name = "arrivalStation", nullable = true, length = 100)
    private String arrivalStation;

    @Column(name = "departureTime", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrivalTime", nullable = true)
    private LocalDateTime arrivalTime;

    @Column(name = "price", nullable = true)
    private Integer price;

    @Column(name = "availableSeats", nullable = true)
    private Integer availableSeats;

    @Enumerated(EnumType.STRING)
    @Column(name = "tripStatus", nullable = false)
    private TripStatus tripStatus;

    public Trips() {
        this.tripId = 0;
        this.routeId = 0;
        this.busId = 0;
        this.driverId = 0;
        this.departureStation = "";
        this.arrivalStation = "";
        this.departureTime = LocalDateTime.now();
        this.arrivalTime = null;
        this.price = 0;
        this.availableSeats = 0;
        this.tripStatus = TripStatus.Scheduled;
    }

    public Trips(int tripId, int routeId, int busId, int driverId, String departureStation, String arrivalStation,
                LocalDateTime departureTime, LocalDateTime arrivalTime, Integer price, Integer availableSeats, TripStatus tripStatus) {
        this.tripId = tripId;
        this.routeId = routeId;
        this.busId = busId;
        this.driverId = driverId;
>>>>>>> origin/Loc_Branch
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.availableSeats = availableSeats;
        this.tripStatus = tripStatus;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/Loc_Branch
