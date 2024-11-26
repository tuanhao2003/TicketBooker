package com.example.ticketbooker.Util.Mapper;

import com.example.ticketbooker.DTO.Invoice.AddInvoiceDTO;
import com.example.ticketbooker.DTO.Invoice.ResponseInvoiceDTO;
import com.example.ticketbooker.Entity.Invoices;
import com.example.ticketbooker.Entity.Invoices;

import java.util.ArrayList;

public class InvoiceMapper {
    public static Invoices fromAdd(AddInvoiceDTO dto) {
        return Invoices.builder()
                .totalAmount(dto.getTotalAmount())
                .paymentStatus(dto.getPaymentStatus())
                .paymentTime(dto.getPaymentTime())
                .paymentMethod(dto.getPaymentMethod())
                .build();
    }
    public static ResponseInvoiceDTO toResponseDTO(ArrayList<Invoices> invoices) {
        return ResponseInvoiceDTO.builder()
                .invoicesCount(invoices.size())
                .listInvoices(invoices)
                .build();
    }
}
