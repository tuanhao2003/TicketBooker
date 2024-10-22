package com.example.ticketbooker.Util.Mapper;


import com.example.ticketbooker.DTO.Routes.AddRouteDTO;
import com.example.ticketbooker.DTO.Routes.UpdateRouteDTO;
import com.example.ticketbooker.Entity.Routes;

public class RouteMapper {
    public static Routes fromAdd (AddRouteDTO dto){
        return Routes.builder()
                .id(dto.getRouteId())
                .departureLocation(dto.getDepartureLocation())
                .arrivalLocation(dto.getArrivalLocation())
                .routeStatus(dto.getStatus())
                .build();
    }
    public static Routes fromUpdate (int id, UpdateRouteDTO dto){
        return Routes.builder()
                .id(dto.getRouteId())
                .departureLocation(dto.getDepartureLocation())
                .arrivalLocation(dto.getArrivalLocation())
                .routeStatus(dto.getStatus())
                .build();
    }
}