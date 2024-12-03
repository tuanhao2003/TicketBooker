package com.example.ticketbooker.DTO.Ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaymentInforResponse {
    private Integer id;
    private String customerName;
    private String customerPhone;
    private LocalDate paymentTime;
    private String email;
    private Integer totalAmount;
    private LocalTime estimatedTime;
    private String departureLocation;
    private String arrivalLocation;
    private LocalDate departureTime;
    private LocalDate arrivalTime;

}
