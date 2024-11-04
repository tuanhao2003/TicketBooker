package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Ticket.TicketDTO;
import com.example.ticketbooker.Entity.Buses;
import com.example.ticketbooker.Entity.Routes;
import com.example.ticketbooker.DTO.Bus.BusDTO;
import com.example.ticketbooker.Util.Mapper.BusMapper;
import com.example.ticketbooker.Repository.BusRepo;
import com.example.ticketbooker.Repository.RouteRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BusService {

    @Autowired
    private BusRepo busRepository;

    public List<BusDTO> findAll()
    {
        return busRepository.findAll().stream()
                .map(BusMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<BusDTO> findById(Integer id) {
        return busRepository.findById(id)
                .map(BusMapper::toDto);
    }

    public BusDTO save(BusDTO busDTO)
    {
        Buses bus = BusMapper.toEntity(busDTO);
        Buses savedBus = busRepository.save(bus);
        return BusMapper.toDto(savedBus);
    }

    public void delete(Integer id) {
        busRepository.deleteById(id);
    }
}
