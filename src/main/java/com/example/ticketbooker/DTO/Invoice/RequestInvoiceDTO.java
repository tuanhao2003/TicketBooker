package com.example.ticketbooker.DTO.Invoice;

import com.example.ticketbooker.Util.Enum.PaymentMethod;
import com.example.ticketbooker.Util.Enum.PaymentStatus;
import lombok.Data;

@Data
public class RequestInvoiceDTO {
    private Integer totalAmount;
    private PaymentStatus paymentStatus;
    private PaymentMethod paymentMethod;
    public RequestInvoiceDTO() {
        this.totalAmount = null;
        this.paymentStatus= PaymentStatus.PAID;
        this.paymentMethod = PaymentMethod.CASH;
    }
    public RequestInvoiceDTO(Integer totalAmount, PaymentStatus paymentStatus, PaymentMethod paymentMethod) {
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
    }

}
