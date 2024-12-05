package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Ticket.TicketIdRequest;
import com.example.ticketbooker.Entity.Account;
import com.example.ticketbooker.Service.*;
import com.example.ticketbooker.Util.Enum.TicketStatus;
import com.example.ticketbooker.Util.Mapper.AccountMapper;
import com.example.ticketbooker.Util.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.ticketbooker.DTO.Invoice.AddInvoiceDTO;
import com.example.ticketbooker.DTO.Ticket.AddTicketRequest;
import com.example.ticketbooker.DTO.Trips.ResponseTripDTO;
import com.example.ticketbooker.DTO.Trips.SearchTripRequest;
import com.example.ticketbooker.Util.Enum.PaymentMethod;
import com.example.ticketbooker.Util.Enum.PaymentStatus;
import com.example.ticketbooker.Util.Utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping("/fuba")
public class MainController {
    @Autowired
    private TripService tripService;
    @Autowired
    TicketService ticketService;
    @Autowired
    InvoiceService invoiceService;
    @Autowired
    SeatsService seatsService;
    @Autowired
    AccountService accountService;

    @GetMapping()
    public String showMainPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Check if the user is logged in (not anonymous)
        boolean isLoggedIn = SecurityUtils.isLoggedIn();
        if (isLoggedIn) {
            Object principal = authentication.getPrincipal();
            Account account = SecurityUtils.extractAccount(principal);
            if (account != null) {
                model.addAttribute("fullname", account.getUser().getFullName());
            } else {
                model.addAttribute("fullname", "not logedin");
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
    public String showBooking(@RequestParam int tripId, Model model, HttpServletResponse response) {
        model.addAttribute("bookingInformation", tripService.getTripById(tripId));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isLoggedIn = SecurityUtils.isLoggedIn();
        if (isLoggedIn) {
            Account account = SecurityUtils.extractAccount(authentication.getPrincipal());
            Integer accountId = account.getId();
            model.addAttribute("accountId", accountId);
            model.addAttribute("fullname", account.getUser().getFullName());
            model.addAttribute("phone", account.getUser().getPhone());
            model.addAttribute("email", account.getEmail());
            CookieUtils.addCookie(response, "bookerId", Integer.toString(accountId), "/", -1);
            return "View/User/Basic/Booking";
        }
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
    public String showPaymentSuccess(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam Map<String, String> allParams) {
        int tripId = Integer.parseInt(CookieUtils.getCookieValue(request, "tripId", "0"));
        String customerName = CookieUtils.getCookieValue(request, "customerName", "");

        int bookerId = Integer.parseInt(CookieUtils.getCookieValue(request, "bookerId", "0"));
        String grandTotal = CookieUtils.getCookieValue(request, "grandTotal", "");
        String email = CookieUtils.getCookieValue(request, "email", "");
        String customerPhone = CookieUtils.getCookieValue(request, "customerPhone", "");
        String seadIds = CookieUtils.getCookieValue(request, "seatIds", "") + " ";
        String[] seatIdList = seadIds.split(" ");

        String paymentStatusParam = allParams.get("paymentStatus");
        int paymentStatus = (paymentStatusParam != null) ? Integer.parseInt(paymentStatusParam) : 0;

        if (paymentStatus == 1) {
            AddInvoiceDTO addInvoiceDTO = new AddInvoiceDTO(
                    Integer.parseInt(grandTotal),
                    paymentStatus == 1 ? PaymentStatus.PAID : PaymentStatus.PENDING,
                    LocalDateTime.now(),
                    PaymentMethod.EWALLET
            );

            int invoiceCreated = invoiceService.addInvoice(addInvoiceDTO);

            for (String s : seatIdList) {
                AddTicketRequest addRequest = new AddTicketRequest();
                addRequest.setCustomerName(customerName);
                addRequest.setCustomerPhone(customerPhone);
                addRequest.setTrip(tripService.getTripById(tripId).getListTrips().get(0));
                addRequest.setSeat(seatsService.getSeatById(Integer.parseInt(s)));
                if(bookerId != 0) {
                    addRequest.setBooker(AccountMapper.toEntity(accountService.getAccountById(bookerId)));
                }
                addRequest.setTicketStatus(TicketStatus.BOOKED);
                addRequest.setInvoices(invoiceService.getById(invoiceCreated));
                ticketService.addTicket(addRequest);
            }
            CookieUtils.addCookie(response, "paymentStatus", Integer.toString(paymentStatus), "/", -1);
        } else if (paymentStatus == 0) {
            for (String s : seatIdList) {
                ticketService.deleteTicket(new TicketIdRequest(Integer.parseInt(s)));
            }
        }
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
