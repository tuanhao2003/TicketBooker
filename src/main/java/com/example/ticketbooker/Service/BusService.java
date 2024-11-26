package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Bus.BusDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BusService {
    List<BusDTO> getAllBuses();
    BusDTO getBusById(int id);
    BusDTO createBus(BusDTO busDTO);
    boolean updateBus(BusDTO busDTO);
    void deleteBus(Integer id);
    Page<BusDTO> getAllBuses(Pageable pageable);
}
