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
            @RequestParam(value = "period", defaultValue = "Day") String period,  // Default period to 'Day'
            @RequestParam(value = "date", defaultValue = "#{T(java.time.LocalDate).now()}")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate selectedDate,  // Default date to today's date
            Model model) {

        // Lấy thông tin doanh thu dựa trên period và date đã được xác định
        RevenueStatsDTO stats = invoiceService.getRevenueStats(period, selectedDate);
        model.addAttribute("stats", stats);

        return "View/Admin/Statistics/Stats";  // Trả về view của Thymeleaf
    }
}
