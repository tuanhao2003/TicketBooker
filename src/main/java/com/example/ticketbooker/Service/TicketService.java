package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Ticket.TicketDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TicketService {
    List<TicketDTO> getAllTickets();
    TicketDTO getTicketById(int id);
    TicketDTO createTicket(TicketDTO ticketDTO);
    boolean updateTicket(TicketDTO ticketDTO);
    void deleteTicket(Integer id);
    Page<TicketDTO> getAllTickets(Pageable pageable);
}
