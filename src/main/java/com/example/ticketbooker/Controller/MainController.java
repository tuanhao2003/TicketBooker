package com.example.ticketbooker.Controller;

import com.example.ticketbooker.Entity.Account;
import com.example.ticketbooker.Util.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.ticketbooker.DTO.Invoice.AddInvoiceDTO;
import com.example.ticketbooker.DTO.Ticket.AddTicketRequest;
import com.example.ticketbooker.DTO.Trips.ResponseTripDTO;
import com.example.ticketbooker.DTO.Trips.SearchTripRequest;
import com.example.ticketbooker.Service.InvoiceService;
import com.example.ticketbooker.Service.TicketService;
import com.example.ticketbooker.Service.TripService;
import com.example.ticketbooker.Util.Enum.PaymentMethod;
import com.example.ticketbooker.Util.Enum.PaymentStatus;
import com.example.ticketbooker.Util.Utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
@RequestMapping("/fuba")
public class MainController {
    @Autowired
    private TripService tripService;
    @Autowired
    TicketService ticketService;
    @Autowired
    InvoiceService invoiceService;

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
        model.addAttribute("searchData", new SearchTripRequest());
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
    public String showtrip(@ModelAttribute("responseTripDTO") ResponseTripDTO responseTripDTO, Model model) {
        if (!model.containsAttribute("responseTripDTO")) {
            model.addAttribute("responseTripDTO", new ResponseTripDTO());
        }
        return "View/User/Basic/FindTrip";
    }

    @PostMapping("/search-trips")
    public String searchTrips(@ModelAttribute("searchData") SearchTripRequest searchTripRequest, RedirectAttributes redirectAttributes) {
        ResponseTripDTO responseTripDTO = tripService.searchTrip(searchTripRequest);
        redirectAttributes.addFlashAttribute("responseTripDTO", responseTripDTO);
        return "redirect:/fuba/find-trip";
    }

    @GetMapping("/booking")
    public String showBooking(@RequestParam int tripId, Model model) {
        model.addAttribute("bookingInformation", tripService.getTripById(tripId));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = SecurityUtils.extractAccount(authentication.getPrincipal());

        model.addAttribute("fullname",account.getUser().getFullName());
        model.addAttribute("phone",account.getUser().getPhone());
        model.addAttribute("email",account.getEmail());
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

    @GetMapping("/thankyou")
    public String showPaymentSuccess(HttpServletRequest request, Model model, @RequestParam int paymentStatus) {
        // cookie: String customerName customerPhone email grandTotal seatIds int tripId bookerId(them vao cookie) (tao dc seat vs invoice)
        // ticket can them ticketStatus (used)

        int tripId = Integer.parseInt(CookieUtils.getCookieValue(request, "tripId", "0"));
        String customerName = CookieUtils.getCookieValue(request, "customerName", "");

        String grandTotal = CookieUtils.getCookieValue(request, "grandTotal", "");
        String email = CookieUtils.getCookieValue(request, "email", "");
        String customerPhone = CookieUtils.getCookieValue(request, "customerPhone", "");
        String seadIds = CookieUtils.getCookieValue(request, "seadIds", "");

        AddInvoiceDTO addInvoiceDTO = new AddInvoiceDTO(
                Integer.parseInt(grandTotal),
                paymentStatus == 1 ? PaymentStatus.PAID : PaymentStatus.PENDING,
                LocalDateTime.now(),
                PaymentMethod.EWALLET
        );

        int invoiceCreated = invoiceService.addInvoice(addInvoiceDTO);

        AddTicketRequest addRequest = new AddTicketRequest();

        CookieUtils.getCookieValue(request, "customerName", "");
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
