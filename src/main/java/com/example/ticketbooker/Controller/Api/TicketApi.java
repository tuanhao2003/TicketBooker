package com.example.ticketbooker.Controller.Api;

import com.example.ticketbooker.DTO.Ticket.TicketDTO;
import com.example.ticketbooker.Service.ServiceImp.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketApi {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public List<TicketDTO> getAllTickets() {
        return ticketService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Integer id) {
        return ticketService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TicketDTO createTicket(@RequestBody TicketDTO ticketDTO) {
        return ticketService.save(ticketDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable Integer id, @RequestBody TicketDTO ticketDTO) {
        ticketDTO.setId(id);
        TicketDTO updatedTicket = ticketService.save(ticketDTO);
        return ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Integer id) {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
