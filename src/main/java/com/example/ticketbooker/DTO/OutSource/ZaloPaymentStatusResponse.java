package com.example.ticketbooker.DTO.OutSource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZaloPaymentStatusResponse {
    private Integer returnCode;
    private boolean isProcessing;
    private String returnMessage;
}
