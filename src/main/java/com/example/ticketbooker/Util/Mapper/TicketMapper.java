package com.example.ticketbooker.Util.Mapper;

import com.example.ticketbooker.DTO.Ticket.AddTicketRequest;
import com.example.ticketbooker.DTO.Ticket.PaymentInforResponse;
import com.example.ticketbooker.DTO.Ticket.TicketResponse;
import com.example.ticketbooker.DTO.Ticket.UpdateTicketRequest;
import com.example.ticketbooker.Entity.Tickets;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class TicketMapper {
    public static Tickets fromAdd(AddTicketRequest dto) {
        return Tickets.builder()
                .trip(dto.getTrip())
                .booker(dto.getBooker())
                .customerName(dto.getCustomerName())
                .customerPhone(dto.getCustomerPhone())
                .seat(dto.getSeat())
                .ticketStatus(dto.getTicketStatus())
                .build();
    }

    public static Tickets fromUpdate(UpdateTicketRequest dto) {
        return Tickets.builder()
                .id(dto.getId())
                .trip(dto.getTrip())
                .booker(dto.getBooker())
                .customerName(dto.getCustomerName())
                .customerPhone(dto.getCustomerPhone())
                .seat(dto.getSeat())
                .qrCode(dto.getQrCode())
                .ticketStatus(dto.getTicketStatus())
                .build();
    }

    public static ArrayList<Tickets> fromResponse(TicketResponse dto) {
        ArrayList<Tickets> response = new ArrayList<>();
        dto.getListTickets().forEach(tk -> {
            response.add(Tickets.builder()
                            .id(tk.getId())
                            .trip(tk.getTrip())
                            .booker(tk.getBooker())
                            .customerName(tk.getCustomerName())
                            .customerPhone(tk.getCustomerPhone())
                            .seat(tk.getSeat())
                            .qrCode(tk.getQrCode())
                            .ticketStatus(tk.getTicketStatus())
                            .build());
        });

        return response;
    }

    public static UpdateTicketRequest toUpdateDTO(Tickets entity) {
        return UpdateTicketRequest.builder()
                .id(entity.getId())
                .trip(entity.getTrip())
                .booker(entity.getBooker())
                .customerName(entity.getCustomerName())
                .customerPhone(entity.getCustomerPhone())
                .seat(entity.getSeat())
                .qrCode(entity.getQrCode())
                .ticketStatus(entity.getTicketStatus())
                .build();
    }
    public static TicketResponse toResponseDTO(ArrayList<Tickets> listEntities) {
        return TicketResponse.builder()
                .ticketsCount(listEntities.size())
                .listTickets(listEntities)
                .build();
    }

    public static PaymentInforResponse toPaymentInfor(Tickets entity) {
        return PaymentInforResponse.builder()
                .customerName(entity.getCustomerName())
                .customerPhone(entity.getCustomerPhone())
                .paymentTime(LocalDate.from(entity.getInvoice().getPaymentTime()))
                .totalAmount(entity.getInvoice().getTotalAmount())
                .build();
    }
}


