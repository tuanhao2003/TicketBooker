package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Routes.AddRouteDTO;
import com.example.ticketbooker.DTO.Routes.UpdateRouteDTO;
import com.example.ticketbooker.Entity.Routes;
import com.example.ticketbooker.Util.Enum.RouteStatus;

import java.util.ArrayList;

public interface RouteService {
    public boolean addRoute(AddRouteDTO dto);
    public boolean updateRoute(UpdateRouteDTO dto);
    public boolean deleteRoute(int id);
    public Routes getRoute(int id);
    public ArrayList<Routes> findAllRoutes();
    public ArrayList<Routes> findByStatus(RouteStatus status);
    public ArrayList<Routes> findByDepartureLocation(String departureLocation);
    public ArrayList<Routes> findByArrivalLocation(String arrivalLocation);
}
