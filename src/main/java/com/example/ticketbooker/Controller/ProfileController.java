package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Ticket.TicketResponse;
import com.example.ticketbooker.Service.TicketService;
import com.example.ticketbooker.Service.UserService;
import com.example.ticketbooker.Util.Enum.TicketStatus;
import jakarta.servlet.http.HttpSession;
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
    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public String showInfo(HttpSession session, Model model) {

        String username = (String) session.getAttribute("username");
        String email = (String) session.getAttribute("email");

        model.addAttribute("email", email);
        model.addAttribute("username", username);
        return "View/User/Registered/Profile/Info";
    }

    @GetMapping("/history-booking/{accountId}")
    public String showHistoryBooking(@PathVariable int accountId, Model model) {
        TicketResponse ticketResponse = ticketService.getTicketsByAccountId(accountId);
        model.addAttribute("ticketResponse", ticketResponse);
        // Lấy tất cả các giá trị của TicketStatus
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