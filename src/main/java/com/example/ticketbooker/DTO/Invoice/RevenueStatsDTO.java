package com.example.ticketbooker.DTO.Invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RevenueStatsDTO {
    private String period; // "Day", "Month", "Year"
    private LocalDate selectedDate;
    private double currentPeriodRevenue;
    private double previousPeriodRevenue;
}
