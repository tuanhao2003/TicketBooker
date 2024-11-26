package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Ticket.*;

import java.util.List;

public interface TicketService {
    boolean addTicket(AddTicketRequest dto);
    boolean updateTicket(UpdateTicketRequest dto);
    boolean deleteTicket(TicketIdRequest dto);
    TicketResponse getAllTickets();
    TicketResponse getTicketById(TicketIdRequest dto);
    PaymentInforResponse getPaymentInfo(PaymentInforRequest request);
}
