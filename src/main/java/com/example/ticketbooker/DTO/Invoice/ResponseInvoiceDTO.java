package com.example.ticketbooker.DTO.Invoice;

import com.example.ticketbooker.Entity.Invoices;
import com.example.ticketbooker.Entity.Trips;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
@Data
@Builder
public class ResponseInvoiceDTO {
    private int invoicesCount;
    private ArrayList<Invoices> listInvoices;

    public ResponseInvoiceDTO() {
        this.invoicesCount = 0;
        this.listInvoices = new ArrayList<>();
    }

    public ResponseInvoiceDTO(int invoicesCount, ArrayList<Invoices> listInvoices) {
        this.invoicesCount = invoicesCount;
        this.listInvoices = listInvoices;
    }
}
