package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Ticket.*;
import com.example.ticketbooker.DTO.Users.UserResponse;
import com.example.ticketbooker.Entity.Tickets;
import com.example.ticketbooker.Entity.Users;
import com.example.ticketbooker.Repository.TicketRepo;
import com.example.ticketbooker.Service.TicketService;
import com.example.ticketbooker.Util.Enum.TicketStatus;
import com.example.ticketbooker.Util.Mapper.TicketMapper;
import com.example.ticketbooker.Util.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImp implements TicketService {
    @Autowired
    private TicketRepo ticketRepository;

    @Override
    public boolean addTicket(AddTicketRequest dto) {
        try {
            Tickets ticket = TicketMapper.fromAdd(dto);
            ticketRepository.save(ticket);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateTicket(UpdateTicketRequest dto) {
        try {
            Tickets ticket = TicketMapper.fromUpdate(dto);
            ticketRepository.save(ticket);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteTicket(TicketIdRequest dto) {
        try {
            ticketRepository.deleteById(dto.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public TicketResponse getAllTickets() {
        TicketResponse result = new TicketResponse();
        try {
            result = TicketMapper.toResponseDTO(this.ticketRepository.findAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }

    @Override
    public TicketResponse getTicketById(TicketIdRequest dto) {
        TicketResponse result = new TicketResponse();
        try {
            result = TicketMapper.toResponseDTO(this.ticketRepository.findAllById(dto.getId()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }

    @Override
    public PaymentInforResponse getPaymentInfo(PaymentInforRequest request) {
        PaymentInforResponse result = new PaymentInforResponse();
        try {
            result = this.ticketRepository.findAllById(request.getTicketId()).size()==1 ? TicketMapper.toPaymentInfor( this.ticketRepository.findAllById(request.getTicketId()).get(0)) : new PaymentInforResponse();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public TicketResponse getTicketsByAccountId(int accountId) {
        TicketResponse result = new TicketResponse();
        try {
            List<Tickets> tickets = ticketRepository.findAllByBookerId(accountId);
            result.setTicketsCount(tickets.size());
            result.setListTickets(new ArrayList<>(tickets));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public TicketResponse searchTickets(int accountId, Integer ticketId, LocalDate departureDate, String route, TicketStatus status) {
        TicketResponse result = new TicketResponse();
        try {
            List<Tickets> tickets = ticketRepository.searchTickets(accountId, ticketId, departureDate, route, status);
            result.setTicketsCount(tickets.size());
            result.setListTickets(new ArrayList<>(tickets));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
