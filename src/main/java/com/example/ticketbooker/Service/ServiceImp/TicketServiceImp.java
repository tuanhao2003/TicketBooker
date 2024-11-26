package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Ticket.TicketDTO;
import com.example.ticketbooker.Entity.Tickets;
import com.example.ticketbooker.Entity.Users;
import com.example.ticketbooker.Repository.TicketRepo;
import com.example.ticketbooker.Service.TicketService;
import com.example.ticketbooker.Util.Mapper.TicketMapper;
import com.example.ticketbooker.Util.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImp implements TicketService {

    @Autowired
    private TicketRepo ticketRepository;

    @Override
    public List<TicketDTO> getAllTickets() {
        List<Tickets> tickets = ticketRepository.findAll();
        List<TicketDTO> dtos = new ArrayList<>();
        tickets.forEach(ticket -> dtos.add(TicketMapper.toDTO(ticket)));
        return dtos;
    }

    @Override
    public Page<TicketDTO> getAllTickets(Pageable pageable) {
        Page<Tickets> tickets = ticketRepository.findAll(pageable);
        return tickets.map(TicketMapper::toDTO);
    }

    @Override
    public TicketDTO getTicketById(int id) {
        TicketDTO result = new TicketDTO();
        try {
            result = TicketMapper.toDTO(this.ticketRepository.findById(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }

    @Override
    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Tickets ticket = TicketMapper.toEntity(ticketDTO);
        Tickets savedTicket = ticketRepository.save(ticket);
        return TicketMapper.toDTO(ticketRepository.save(ticket));
    }

    @Override
    public boolean updateTicket(TicketDTO ticketDTO) {
        try {
            Tickets ticket = TicketMapper.toEntity(ticketDTO);
            this.ticketRepository.save(ticket);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void deleteTicket(Integer id) {
        ticketRepository.deleteById(id);
    }
}
