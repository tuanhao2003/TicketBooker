package com.example.ticketbooker.Util.Mapper;

import com.example.ticketbooker.Entity.Tickets;
import com.example.ticketbooker.DTO.Ticket.TicketDTO;
import com.example.ticketbooker.Repository.AccountRepo;
import com.example.ticketbooker.Repository.InvoiceRepo;
import com.example.ticketbooker.Repository.SeatsRepo;
import com.example.ticketbooker.Repository.TripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class TicketMapper {

    static public TicketDTO toDTO(Tickets ticket) {
        if (ticket == null) return null;

        TicketDTO dto = new TicketDTO();
        dto.setId(ticket.getId());
        dto.setTripId(ticket.getTrip());
        dto.setBookerId(ticket.getBooker());
        if (ticket.getInvoice() != null) {
            dto.setInvoiceId(ticket.getInvoice());
        }
        dto.setCustomerName(ticket.getCustomerName());
        dto.setCustomerPhone(ticket.getCustomerPhone());
        dto.setSeatId(ticket.getSeat());
        dto.setQrCode(ticket.getQrCode());
        dto.setTicketStatus(ticket.getTicketStatus());
        return dto;
    }

    static public Tickets toEntity(TicketDTO dto) {
        if (dto == null) return null;

        Tickets ticket = new Tickets();
        ticket.setId(dto.getId());

        ticket.setTrip(dto.getTripId());
        ticket.setBooker(dto.getBookerId());
        ticket.setInvoice(dto.getInvoiceId());
        ticket.setSeat(dto.getSeatId());

        ticket.setCustomerName(dto.getCustomerName());
        ticket.setCustomerPhone(dto.getCustomerPhone());
        ticket.setQrCode(dto.getQrCode());
        ticket.setTicketStatus(dto.getTicketStatus());

        return ticket;
    }
}


