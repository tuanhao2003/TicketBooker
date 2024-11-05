package com.example.ticketbooker.Util.Mapper;

import com.example.ticketbooker.Entity.Buses;
import com.example.ticketbooker.DTO.Bus.BusDTO;
import com.example.ticketbooker.Entity.Routes;

public class BusMapper {
    public static BusDTO toDto(Buses bus) {
        BusDTO busDTO = new BusDTO();
        busDTO.setId(bus.getId());
        busDTO.setRouteId(bus.getRoute().getId());
        busDTO.setLicensePlate(bus.getLicensePlate());
        busDTO.setBusType(bus.getBusType());
        busDTO.setCapacity(bus.getCapacity());
        busDTO.setBusStatus(bus.getBusStatus());
        return busDTO;
    }

    public static Buses toEntity(BusDTO busDTO) {
        Buses bus = new Buses();
        bus.setId(busDTO.getId());
        bus.setLicensePlate(busDTO.getLicensePlate());
        bus.setBusType(busDTO.getBusType());
        bus.setCapacity(busDTO.getCapacity());
        bus.setBusStatus(busDTO.getBusStatus());
        return bus;
    }
}
