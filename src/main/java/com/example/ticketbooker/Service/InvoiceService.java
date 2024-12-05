package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Invoice.AddInvoiceDTO;
import com.example.ticketbooker.DTO.Invoice.RequestInvoiceDTO;
import com.example.ticketbooker.DTO.Invoice.ResponseInvoiceDTO;
import com.example.ticketbooker.DTO.Invoice.RevenueStatsDTO;
import com.example.ticketbooker.Entity.Invoices;

import java.time.LocalDate;

public interface InvoiceService {
    public int addInvoice(AddInvoiceDTO dto);
    public ResponseInvoiceDTO getAllInvoices();
    ResponseInvoiceDTO searchInvoices(RequestInvoiceDTO requestDTO);
    Invoices getById(int id);
    RevenueStatsDTO getRevenueStats(String period, LocalDate selectedDate);
}
