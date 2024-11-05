package com.example.ticketbooker.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "seats")
public class Seats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seatId", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tripId")
    private Trips trip;

    @Column(name = "seatCode", nullable = false, length = 10)
    private String seatCode;

    public Seats() {
        this.id = null;
        this.seatCode = "";
        this.trip = new Trips();
    }

    public Seats(Integer id, Trips trip, String seatCode) {
        this.id = id;
        this.trip = trip;
        this.seatCode = seatCode;
    }
}