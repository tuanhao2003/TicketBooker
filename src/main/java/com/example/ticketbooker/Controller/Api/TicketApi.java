package com.example.ticketbooker.Controller.Api;

import com.example.ticketbooker.DTO.Ticket.TicketDTO;
import com.example.ticketbooker.Service.ServiceImp.TicketServiceImp;
import com.example.ticketbooker.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketApi {

    @Autowired
    private TicketService ticketsService;

    // Xóa ticket
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable("id") Integer id) {
        if (ticketsService.getTicketById(id) != null) {
            ticketsService.deleteTicket(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
