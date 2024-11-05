package com.example.ticketbooker.DTO.Ticket;

import com.example.ticketbooker.Entity.Account;
import com.example.ticketbooker.Entity.Invoices;
import com.example.ticketbooker.Entity.Seats;
import com.example.ticketbooker.Entity.Trips;
import com.example.ticketbooker.Util.Enum.TicketStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindTicketDTO {
    private Trips trip;
    private Account booker;
    private Invoices invoice;
    private String customerName;
    private String customerPhone;
    private Seats seat;
    private String qrCode;
    private TicketStatus ticketStatus;

    public FindTicketDTO() {
        this.trip = new Trips();
        this.booker = new Account();
        this.invoice = null;
        this.customerName = "";
        this.customerPhone = "";
        this.seat = new Seats();
        this.qrCode = null;
        this.ticketStatus = TicketStatus.BOOKED;
    }

    public FindTicketDTO(Trips trip, Account booker, Invoices invoice, String customerName, String customerPhone, Seats seat, String qrCode, TicketStatus ticketStatus) {
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
