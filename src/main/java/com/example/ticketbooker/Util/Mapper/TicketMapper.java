package com.example.ticketbooker.Util.Mapper;

import com.example.ticketbooker.Entity.Tickets;
import com.example.ticketbooker.DTO.Ticket.TicketDTO;

import java.util.ArrayList;

public class TicketMapper {
    public static TicketDTO toDto(Tickets ticket) {
        TicketDTO dto = new TicketDTO();
        dto.setId(ticket.getId());
        dto.setTripId(ticket.getTrip().getId());
        dto.setBookerId(ticket.getBooker().getId());
        dto.setInvoiceId(ticket.getInvoice() != null ? ticket.getInvoice().getId() : null);
        dto.setCustomerName(ticket.getCustomerName());
        dto.setCustomerPhone(ticket.getCustomerPhone());
        dto.setSeatId(ticket.getSeat().getId());
        dto.setQrCode(ticket.getQrCode());
        dto.setTicketStatus(ticket.getTicketStatus());
        return dto;
    }

    public static Tickets toEntity(TicketDTO dto) {
        Tickets ticket = new Tickets();
        ticket.setId(dto.getId());
        // Set other fields (trip, booker, invoice, seat) based on IDs
        ticket.setCustomerName(dto.getCustomerName());
        ticket.setCustomerPhone(dto.getCustomerPhone());
        ticket.setQrCode(dto.getQrCode());
        ticket.setTicketStatus(dto.getTicketStatus());
        return ticket;
    }
}


