package com.example.ticketbooker.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/fuba")
public class MainController {
    @GetMapping()
    public String showMainPage() {
        return "View/User/TrangChu";
    }

    @GetMapping("/about-us")
    public String showAboutUs() {
        return "View/User/AboutUs";
    }

    @GetMapping("/contact-us")
    public String showContactUs() {
        return "View/User/ContactUs";
    }

    @GetMapping("/ticket-lookup")
    public String showTicketLookup() {
        return "View/User/LookUpTicket";
    }

    @GetMapping("/history-booking")
    public String showHistoryBooking() {
        return "View/User/TicketHistory";
    }

    @GetMapping("/find-trip")
    public String showBooking() {
        return "View/User/FindTrip";
    }
}
