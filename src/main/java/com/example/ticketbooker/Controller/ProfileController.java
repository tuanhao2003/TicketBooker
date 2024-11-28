package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Ticket.TicketResponse;
import com.example.ticketbooker.Service.TicketService;
import com.example.ticketbooker.Util.Enum.TicketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/fuba")
public class ProfileController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/info")
    public String showInfo() {
        return "View/User/Registered/Profile/Info";
    }

    @GetMapping("/history-booking/{accountId}")
    public String showHistoryBooking(@PathVariable int accountId,
                                     @RequestParam(required = false) Integer ticketId,
                                     @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
                                     @RequestParam(required = false) String route,
                                     @RequestParam(required = false) TicketStatus status,
                                     Model model) {
        TicketResponse ticketResponse = ticketService.searchTickets(accountId, ticketId, departureDate, route, status);
        model.addAttribute("ticketResponse", ticketResponse);
        model.addAttribute("ticketStatuses", TicketStatus.values());
        return "View/User/Registered/Profile/TicketHistory";
    }

    @GetMapping("/address")
    public String showAddress() {
        return "View/User/Registered/Profile/Address";
    }

    @GetMapping("/reset-password")
    public String showTicketLookup() {
        return "View/User/Registered/Profile/Password";
    }

}