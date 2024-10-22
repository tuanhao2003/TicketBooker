package com.example.ticketbooker.Entity;

import com.example.ticketbooker.Util.Enum.SeatStatus;
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

    @Column(name = "seatStatus", nullable = false)
    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;

    public Seats() {
        this.id = null;
        this.seatStatus = SeatStatus.AVAILABLE;
        this.seatCode = "";
        this.trip = new Trips();
    }

    public Seats(Integer id, Trips trip, String seatCode, SeatStatus seatStatus) {
        this.id = id;
        this.trip = trip;
        this.seatCode = seatCode;
        this.seatStatus = seatStatus;
    }
}