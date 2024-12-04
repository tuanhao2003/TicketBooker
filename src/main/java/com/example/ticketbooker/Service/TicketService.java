package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Ticket.*;
import com.example.ticketbooker.Util.Enum.TicketStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TicketService {
    boolean addTicket(AddTicketRequest dto);
    boolean updateTicket(UpdateTicketRequest dto);
    boolean deleteTicket(TicketIdRequest dto);
    TicketResponse getAllTickets();
    TicketResponse getTicketById(TicketIdRequest dto);
    PaymentInforResponse getPaymentInfo(PaymentInforRequest request);
    TicketResponse getTicketsByAccountId(int accountId);
    TicketResponse searchTickets(int accountId, Integer ticketId, LocalDate departureDate, String route, TicketStatus status);
    TicketStatsDTO getTicketStats(String period, LocalDate selectedDate);
}
