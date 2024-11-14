package com.example.ticketbooker.Controller.Api;

import com.example.ticketbooker.DTO.Invoice.AddInvoiceDTO;
import com.example.ticketbooker.Service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceApi {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/create")
    public boolean createInvoice(@RequestBody AddInvoiceDTO addInvoiceDTO) {
        boolean result = false;
        try {
            result = invoiceService.addInvoice(addInvoiceDTO);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
