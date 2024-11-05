package com.example.ticketbooker.Service;

import com.example.ticketbooker.Controller.Api.RouteApi;
import com.example.ticketbooker.DTO.Routes.AddRouteDTO;
import com.example.ticketbooker.DTO.Routes.RequestRouteIdDTO;
import com.example.ticketbooker.DTO.Routes.ResponseRouteDTO;
import com.example.ticketbooker.DTO.Routes.UpdateRouteDTO;
import com.example.ticketbooker.Entity.Routes;
import com.example.ticketbooker.Util.Enum.RouteStatus;

import java.util.ArrayList;

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
}
