package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Account.AccountDTO;
import com.example.ticketbooker.Entity.Account;
import com.example.ticketbooker.Entity.CustomOAuth2User;
import com.example.ticketbooker.Entity.CustomUserDetails;
import com.example.ticketbooker.Entity.Users;
import com.example.ticketbooker.Service.AccountService;
import com.example.ticketbooker.Service.ServiceImp.AccountServiceImp;
import com.example.ticketbooker.Util.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/fuba")
public class MainController {
    @GetMapping()
    public String showMainPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Check if the user is logged in (not anonymous)
        boolean isLoggedIn = SecurityUtils.isLoggedIn();
        if(isLoggedIn) {
            Object principal = authentication.getPrincipal();
            Account account = SecurityUtils.extractAccount(principal);
            if(account != null) {
                model.addAttribute("fullname", account.getUser().getFullName());
//                model.addAttribute("email", account.getEmail());
//                model.addAttribute("role", account.getRole().toString());
//                model.addAttribute("user", account.getUser());
            }else{
                model.addAttribute("fullname","not logedin");
            }
        }
        model.addAttribute("isLoggedIn", isLoggedIn);
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
