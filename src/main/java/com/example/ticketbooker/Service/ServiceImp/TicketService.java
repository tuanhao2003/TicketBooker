package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Ticket.TicketDTO;
import com.example.ticketbooker.Entity.Tickets;
import com.example.ticketbooker.Repository.TicketRepo;
import com.example.ticketbooker.Util.Mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private TicketRepo ticketRepository;

    public List<TicketDTO> findAll() {
        return ticketRepository.findAll().stream()
                .map(TicketMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<TicketDTO> findById(Integer id) {
        return ticketRepository.findById(id)
                .map(TicketMapper::toDto);
    }

    public TicketDTO save(TicketDTO ticketDTO) {
        Tickets ticket = TicketMapper.toEntity(ticketDTO);
        Tickets savedTicket = ticketRepository.save(ticket);
        return TicketMapper.toDto(savedTicket);
    }

    public void delete(Integer id) {
        ticketRepository.deleteById(id);
    }
}
