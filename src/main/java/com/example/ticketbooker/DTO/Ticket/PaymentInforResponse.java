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
public class PaymentInforResponse {
    private String customerName;
    private String customerPhone;
    private LocalDate paymentTime;
    private Integer totalAmount;
}
