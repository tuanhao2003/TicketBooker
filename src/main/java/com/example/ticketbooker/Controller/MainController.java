package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Account.AccountDTO;
import com.example.ticketbooker.Service.AccountService;
import com.example.ticketbooker.Service.ServiceImp.AccountServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/fuba")
public class MainController {
    @GetMapping()
    public String showMainPage() {
        return "View/User/Basic/TrangChu";
    }

    @GetMapping("/about-us")
    public String showAboutUs() {
        return "View/User/Basic/AboutUs";
    }

    @GetMapping("/contact-us")
    public String showContactUs() {
        return "View/User/Basic/ContactUs";
    }

    @GetMapping("/find-trip")
    public String showtrip() {
        return "View/User/Basic/FindTrip";
    }

    @GetMapping("/booking")
    public String showBooking() {
        return "View/User/Basic/Booking";
    }

    @GetMapping("/paying")
    public String showPaying() {
        return "View/User/Basic/Paying";
    }

    @GetMapping("/ticket-lookup")
    public String showTicketLookup() {
        return "View/User/Basic/LookUpTicket";
    }

    @GetMapping("/payment-success")
    public String showPaymentSuccess() {
        return "View/User/Basic/Thankyou";
    }

    @GetMapping("/login")
    public String login() {
        return "View/Util/Login";
    }

    @GetMapping("/profile")
    public String showProfile() {
        return "View/User/Registered/Profile/TicketHistory";
    }

}
