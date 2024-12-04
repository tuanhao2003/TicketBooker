package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Invoice.RevenueStatsDTO;
import com.example.ticketbooker.DTO.Users.AddUserRequest;
import com.example.ticketbooker.Service.InvoiceService;
import com.example.ticketbooker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public String allUsers(Model model) {
        model.addAttribute("responseDTO", invoiceService.getAllInvoices());
        return "View/Admin/Invoices/ListInvoices";
    }
}
