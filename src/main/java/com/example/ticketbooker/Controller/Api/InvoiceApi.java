package com.example.ticketbooker.Controller.Api;

import com.example.ticketbooker.DTO.Invoice.AddInvoiceDTO;
import com.example.ticketbooker.DTO.Invoice.RequestInvoiceDTO;
import com.example.ticketbooker.DTO.Invoice.ResponseInvoiceDTO;
import com.example.ticketbooker.Service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceApi {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/create")
    public int createInvoice(@RequestBody AddInvoiceDTO addInvoiceDTO) {
        int result = 0;
        try {
            result = invoiceService.addInvoice(addInvoiceDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    @PostMapping("/search")
    public ResponseEntity<ResponseInvoiceDTO> searchInvoices(@RequestBody RequestInvoiceDTO requestDTO) {
        ResponseInvoiceDTO result = invoiceService.searchInvoices(requestDTO);
        return ResponseEntity.ok(result);
    }
}
