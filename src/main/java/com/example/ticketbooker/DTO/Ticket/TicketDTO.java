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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDTO {
    private Integer id;
    private Trips tripId;
    private Account bookerId;
    private Invoices invoiceId;
    private String customerName;
    private String customerPhone;
    private Seats seatId;
    private String qrCode;
    private TicketStatus ticketStatus;
}

