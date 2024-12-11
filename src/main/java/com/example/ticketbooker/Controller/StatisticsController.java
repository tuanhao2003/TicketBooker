package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.ChartData;
import com.example.ticketbooker.DTO.Invoice.RevenueStatsDTO;
import com.example.ticketbooker.DTO.Ticket.TicketStatsDTO;
import com.example.ticketbooker.DTO.Trips.TripStatsDTO;
import com.example.ticketbooker.Service.InvoiceService;
import com.example.ticketbooker.Service.ServiceImp.StatisticsService;
import com.example.ticketbooker.Service.TicketService;
import com.example.ticketbooker.Service.TripService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/thongke")
    public String getStatistics(@RequestParam(value = "startDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
                                @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate endDate,
                                Model model) throws JsonProcessingException {

        if(startDate == null && endDate == null) {
            startDate = LocalDate.now().minusMonths(4);
            endDate = LocalDate.now();
        }
        List<ChartData> chartDataList = statisticsService.getStatistics(startDate, endDate);
        //Đếm só user
        int totalUsers = statisticsService.countAllUser();
        //Láy Hóađơn mới trong tháng
        System.out.println(chartDataList);
        int newMonthlyOrders = 0;
        int newMonthlyTickets = 0;
        for(ChartData chartData : chartDataList) {
            if(chartData.getPaymentDate().isAfter(LocalDate.now().minusMonths(4))) {
                newMonthlyOrders += chartData.getOrderCount();
                newMonthlyTickets += chartData.getTicketCount();
            }
        }
        // Chuyển đổi dữ liệu thành JSON để hiển thị trên biểu đồ
        Map<String, Object> chartDataJson = new HashMap<>();
        chartDataJson.put("ticketCounts", chartDataList.stream()
                .map(data -> Map.of("x", data.getPaymentDate().toString(), "y", data.getTicketCount()))
                .toList());
        chartDataJson.put("orderCounts", chartDataList.stream()
                .map(data -> Map.of("x", data.getPaymentDate().toString(), "y", data.getOrderCount()))
                .toList());
        chartDataJson.put("revenue", chartDataList.stream()
                .map(data -> Map.of("x", data.getPaymentDate().toString(), "y", data.getRevenue()))
                .toList());
        ObjectMapper objectMapper =  new ObjectMapper();
        model.addAttribute("chartDataJson", objectMapper.writeValueAsString(chartDataJson));
        model.addAttribute("newMonthlyOrders", newMonthlyOrders);
        model.addAttribute("newMonthlyTickets", newMonthlyTickets);;
        model.addAttribute("totalUsers", totalUsers);
        return "View/Admin/Statistics/ThongKe";
    }


    @Autowired
    private InvoiceService invoiceService;

    @GetMapping()
    public String statistics() {
        return "View/Admin/Statistics/Statistics";  // Trả về view của Thymeleaf
    }
    @GetMapping("/revenue")
    public String revenueStats(
            @RequestParam(value = "period", defaultValue = "Day") String period,  // Default period to 'Day'
            @RequestParam(value = "date", defaultValue = "#{T(java.time.LocalDate).now()}")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate selectedDate,  // Default date to today's date
            Model model) {

        // Lấy thông tin doanh thu dựa trên period và date đã được xác định
        RevenueStatsDTO stats = invoiceService.getRevenueStats(period, selectedDate);
        model.addAttribute("stats", stats);

        return "View/Admin/Statistics/RevenueStatistics";  // Trả về view của Thymeleaf
    }

    @Autowired
    private TicketService ticketService;

    @GetMapping("/ticket")
    public String ticketCountStats(
            @RequestParam(value = "period", defaultValue = "Day") String period,
            @RequestParam(value = "date", defaultValue = "#{T(java.time.LocalDate).now()}")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate selectedDate,
            Model model) {

        TicketStatsDTO stats = ticketService.getTicketStats(period, selectedDate);
        model.addAttribute("stats", stats);

        return "View/Admin/Statistics/TicketStatistics";
    }

    @Autowired
    private TripService tripService;

    @GetMapping("/trip")
    public String tripCountStats(
            @RequestParam(value = "period", defaultValue = "Day") String period,
            @RequestParam(value = "date", defaultValue = "#{T(java.time.LocalDate).now()}")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate selectedDate,
            Model model) {

        TripStatsDTO stats = tripService.getTripStats(period, selectedDate);
        model.addAttribute("stats", stats);

        return "View/Admin/Statistics/TripStatistics"; // Create this Thymeleaf template
    }
}
