package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Ticket.TicketDTO;

import java.util.List;

public interface TicketService {
    List<TicketDTO> getAllTickets();
    TicketDTO getTicketById(int id);
    TicketDTO createTicket(TicketDTO ticketDTO);
    boolean updateTicket(TicketDTO ticketDTO);
    void deleteTicket(Integer id);
}
