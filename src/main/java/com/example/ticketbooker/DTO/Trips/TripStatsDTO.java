package com.example.ticketbooker.DTO.Trips;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TripStatsDTO {
    private String period;
    private LocalDate selectedDate;
    private long currentPeriodTripCount;
    private long previousPeriodTripCount;
}
