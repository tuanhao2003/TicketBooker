package com.example.ticketbooker.DTO.Ticket;

import com.example.ticketbooker.Entity.Account;
import com.example.ticketbooker.Entity.Invoices;
import com.example.ticketbooker.Entity.Seats;
import com.example.ticketbooker.Entity.Trips;
import com.example.ticketbooker.Util.Enum.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateTicketRequest {
    private int id;
    private Trips trip;
    private Account booker;
    private Invoices invoice;
    private String customerName;
    private String customerPhone;
    private Seats seat;
    private String qrCode;
    private TicketStatus ticketStatus;
}
