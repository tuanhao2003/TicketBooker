package com.example.ticketbooker.DTO.OutSource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZaloPaymentResponse {
    private Integer returnCode;
    private String detailMessage;
    private String returnUrl;
    private String paymentId;
}
