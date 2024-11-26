package com.example.ticketbooker.Util.Mapper;

import com.example.ticketbooker.Entity.Buses;
import com.example.ticketbooker.DTO.Bus.BusDTO;
import org.springframework.stereotype.Component;

@Component
public class BusMapper {
    public static BusDTO toDTO(Buses bus) {
        if (bus == null) return null;

        BusDTO busDTO = new BusDTO();
        busDTO.setId(bus.getId());
        busDTO.setRouteId(bus.getRoute());
        busDTO.setLicensePlate(bus.getLicensePlate());
        busDTO.setBusType(bus.getBusType());
        busDTO.setCapacity(bus.getCapacity());
        busDTO.setBusStatus(bus.getBusStatus());
        return busDTO;
    }

    public static Buses toEntity(BusDTO busDTO) {
        if (busDTO == null) return null;

        Buses bus = new Buses();
        bus.setId(busDTO.getId());
        bus.setRoute(busDTO.getRouteId());
        bus.setLicensePlate(busDTO.getLicensePlate());
        bus.setBusType(busDTO.getBusType());
        bus.setCapacity(busDTO.getCapacity());
        bus.setBusStatus(busDTO.getBusStatus());
        return bus;
    }
}
