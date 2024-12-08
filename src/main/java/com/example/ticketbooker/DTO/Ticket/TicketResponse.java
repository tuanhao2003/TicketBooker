package com.example.ticketbooker.DTO.Ticket;

import com.example.ticketbooker.Entity.*;
import com.example.ticketbooker.Util.Enum.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TicketResponse {
    private int ticketsCount;
    private List<Tickets> listTickets; // Changed to List for more flexibility
    private int currentPage;
    private int totalPages;

    // New constructor for pagination
    public TicketResponse(Page<Tickets> ticketPage) {
        this.ticketsCount = (int) ticketPage.getTotalElements(); // Explicit cast to int
        this.listTickets = ticketPage.getContent();
        this.currentPage = ticketPage.getNumber() + 1; // Page numbers are 0-indexed
        this.totalPages = ticketPage.getTotalPages();
    }

    public static TicketResponse fromList(List<Tickets> tickets) {
        return new TicketResponse(tickets.size(), tickets, 0, 0); // For non-paginated lists
    }
}
