package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Invoice.AddInvoiceDTO;
import com.example.ticketbooker.DTO.Invoice.ResponseInvoiceDTO;

public interface InvoiceService {
    public boolean addInvoice(AddInvoiceDTO dto);
    public ResponseInvoiceDTO getAllInvoices();


}
