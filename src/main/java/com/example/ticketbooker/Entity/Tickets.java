package com.example.ticketbooker.Entity;

import com.example.ticketbooker.Util.Enum.TicketStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Entity
@Table(name = "tickets")
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketId", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tripId")
    private Trips trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookerId")
    @Nullable
    private Account booker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoiceId")
    private Invoices invoice;

    @Column(name = "customerName", nullable = false, length = 100)
    private String customerName;

    @Column(name = "customerPhone", nullable = false, length = 15)
    private String customerPhone;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seatId")
    private Seats seat;

    @Column(name = "qrCode")
    private String qrCode;

    @Column(name = "ticketStatus", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    public Tickets() {
        this.id = null;
        this.trip = new Trips();
        this.booker = new Account();
        this.invoice = null;
        this.customerName = "";
        this.customerPhone = "";
        this.seat = new Seats();
        this.qrCode = null;
        this.ticketStatus = TicketStatus.BOOKED;
    }

    public Tickets(Integer id, Trips trip, Account booker, Invoices invoice, String customerName, String customerPhone, Seats seat, String qrCode, TicketStatus ticketStatus) {
        this.id = id;
        this.trip = trip;
        this.booker = booker;
        this.invoice = invoice;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.seat = seat;
        this.qrCode = qrCode;
        this.ticketStatus = ticketStatus;
    }
}