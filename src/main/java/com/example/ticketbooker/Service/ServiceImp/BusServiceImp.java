package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.Entity.Buses;
import com.example.ticketbooker.DTO.Bus.BusDTO;
import com.example.ticketbooker.Service.BusService;
import com.example.ticketbooker.Util.Mapper.BusMapper;
import com.example.ticketbooker.Repository.BusRepo;
import org.hibernate.bytecode.enhance.spi.interceptor.BytecodeInterceptorLogging_$logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusServiceImp implements BusService {

    @Autowired
    private BusRepo busRepository;

    @Override
    public List<BusDTO> getAllBuses() {
        List<Buses> buses = busRepository.findAll();
        List<BusDTO> dtos = new ArrayList<>();
        buses.forEach(bus -> dtos.add(BusMapper.toDTO(bus)));
        return dtos;
    }

    @Override
    public BusDTO getBusById(int id) {
        BusDTO result = new BusDTO();
        try {
            result = BusMapper.toDTO(this.busRepository.findById(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }

    @Override
    public BusDTO createBus(BusDTO busDTO) {
        Buses bus = BusMapper.toEntity(busDTO);
        Buses savedBus = busRepository.save(bus);
        return BusMapper.toDTO(busRepository.save(bus));
    }

    @Override
    public boolean updateBus(BusDTO busDTO) {
        try {
            Buses bus = BusMapper.toEntity(busDTO);
            this.busRepository.save(bus);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void deleteBus(Integer id) {
        busRepository.deleteById(id);
    }

}
