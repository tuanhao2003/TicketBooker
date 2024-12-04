package com.example.ticketbooker.Service;

import com.example.ticketbooker.Controller.Api.RouteApi;
import com.example.ticketbooker.DTO.Routes.*;
import com.example.ticketbooker.Entity.Routes;
import com.example.ticketbooker.Util.Enum.RouteStatus;

import java.util.ArrayList;
import java.util.List;

public interface RouteService {
    public boolean addRoute(AddRouteDTO dto);
    public boolean updateRoute(UpdateRouteDTO dto);
    public boolean deleteRoute(RequestRouteIdDTO dto);
    public Routes getRoute(Integer id);
    public ResponseRouteDTO findAllRoutes();
    public ResponseRouteDTO findByStatus(RouteStatus status);
    public ResponseRouteDTO findByDepartureLocation(String departureLocation);
    public ResponseRouteDTO findByArrivalLocation(String arrivalLocation);
    public ResponseRouteDTO findByLocation(String arrivalLocation);
    public ResponseRouteDTO findByLocations(SearchRouteRequest request);
    public List<Routes> getAllRoutes();
}
