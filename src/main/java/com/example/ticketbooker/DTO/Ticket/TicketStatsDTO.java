package com.example.ticketbooker.DTO.Ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TicketStatsDTO {
    private String period;
    private LocalDate selectedDate;
    private int currentPeriodTicketCount;
    private int previousPeriodTicketCount;
}
