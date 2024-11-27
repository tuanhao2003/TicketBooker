package com.example.ticketbooker.DTO.OutSource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZaloPaymentRequest {
    private String appUser;
    private long amount;
    private String description;
}
