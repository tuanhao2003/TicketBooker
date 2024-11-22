package com.example.ticketbooker.DTO.Ticket;

import com.example.ticketbooker.Entity.*;
import com.example.ticketbooker.Util.Enum.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TicketResponse {
    private int ticketsCount;
    private ArrayList<Tickets> listTickets;
}
