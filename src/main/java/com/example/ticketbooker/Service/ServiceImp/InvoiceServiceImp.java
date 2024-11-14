package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Invoice.AddInvoiceDTO;
import com.example.ticketbooker.DTO.Invoice.ResponseInvoiceDTO;
import com.example.ticketbooker.Entity.Invoices;
import com.example.ticketbooker.Repository.InvoiceRepo;
import com.example.ticketbooker.Service.InvoiceService;
import com.example.ticketbooker.Util.Mapper.InvoiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImp implements InvoiceService {
    @Autowired
    private InvoiceRepo invoicesRepo;
    @Override
    public boolean addInvoice(AddInvoiceDTO dto) {
        try {
            Invoices invoices = InvoiceMapper.fromAdd(dto);
            this.invoicesRepo.save(invoices);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    @Override
    public ResponseInvoiceDTO getAllInvoices() {
        ResponseInvoiceDTO result = new ResponseInvoiceDTO();
        try {
            result = InvoiceMapper.toResponseDTO(this.invoicesRepo.findAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }
}
