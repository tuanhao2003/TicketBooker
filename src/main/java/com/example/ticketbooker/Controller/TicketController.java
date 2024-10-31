package com.example.ticketbooker.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/tickets")
public class TicketController {
    @GetMapping()
    public String showTicketsPage() {
        return "View/Admin/TicketManagement/Ticket";
    }
}
