package com.example.ticketbooker.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fuba")
public class ProfileController {
    @GetMapping("/info")
    public String showInfo() {
        return "View/User/Registered/Profile/Info";
    }

    @GetMapping("/history-booking")
    public String showHistoryBooking() {
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