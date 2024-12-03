package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Invoice.RevenueStatsDTO;
import com.example.ticketbooker.Service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin/statistics")
public class StatisticsController {

    @Autowired
    private InvoiceService invoiceService;
    @GetMapping()
    public String revenueStats(
            @RequestParam("period") String period,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate selectedDate,
            Model model) {


        RevenueStatsDTO stats = invoiceService.getRevenueStats(period, selectedDate);
        model.addAttribute("stats", stats);
        return "View/Admin/Statistics/Stats"; // Create this Thymeleaf template
    }
}
