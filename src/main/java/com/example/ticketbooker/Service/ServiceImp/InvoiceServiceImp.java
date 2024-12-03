package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Invoice.AddInvoiceDTO;
import com.example.ticketbooker.DTO.Invoice.RequestInvoiceDTO;
import com.example.ticketbooker.DTO.Invoice.ResponseInvoiceDTO;
import com.example.ticketbooker.Entity.Invoices;
import com.example.ticketbooker.Repository.InvoiceRepo;
import com.example.ticketbooker.Service.InvoiceService;
import com.example.ticketbooker.Util.Mapper.InvoiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InvoiceServiceImp implements InvoiceService {
    @Autowired
    private InvoiceRepo invoicesRepo;
    @Override
    public int addInvoice(AddInvoiceDTO dto) {
        try {
            Invoices invoices = InvoiceMapper.fromAdd(dto);
            Invoices savedInvoice = this.invoicesRepo.save(invoices);
            return savedInvoice.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
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
    public ResponseInvoiceDTO searchInvoices(RequestInvoiceDTO requestDTO) {
        ResponseInvoiceDTO result = new ResponseInvoiceDTO();
        try {
            ArrayList<Invoices> filteredInvoices = invoicesRepo.searchInvoices(
                    requestDTO.getTotalAmount(),
                    requestDTO.getPaymentStatus(),
                    requestDTO.getPaymentMethod()
            );
            result = InvoiceMapper.toResponseDTO(filteredInvoices);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

}
