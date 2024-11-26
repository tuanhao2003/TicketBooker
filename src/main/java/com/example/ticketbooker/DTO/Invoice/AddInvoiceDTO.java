package com.example.ticketbooker.DTO.Invoice;

import com.example.ticketbooker.Util.Enum.PaymentMethod;
import com.example.ticketbooker.Util.Enum.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Builder
public class AddInvoiceDTO {
    private Integer totalAmount;
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentTime;
    private PaymentMethod paymentMethod;

    public AddInvoiceDTO() {
        this.totalAmount = null;
        this.paymentStatus = PaymentStatus.PENDING;
        this.paymentTime = null;
        this.paymentMethod = PaymentMethod.CASH;
    }
    public AddInvoiceDTO(Integer totalAmount, PaymentStatus paymentStatus, LocalDateTime paymentTime, PaymentMethod paymentMethod) {
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.paymentTime = paymentTime;
        this.paymentMethod = paymentMethod;
    }
}
