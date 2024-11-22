package com.example.ticketbooker.DTO.Ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaymentInforRequest {
    private Integer ticketId;
    private String customerPhone;
}
