package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Ticket.TicketResponse;
import com.example.ticketbooker.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String showHistoryBooking(@PathVariable int accountId, Model model) {
        TicketResponse ticketResponse = ticketService.getTicketsByAccountId(accountId);
        model.addAttribute("ticketResponse", ticketResponse);
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