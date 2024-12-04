package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Routes.*;
import com.example.ticketbooker.Entity.Routes;
import com.example.ticketbooker.Repository.RouteRepo;
import com.example.ticketbooker.Service.RouteService;
import com.example.ticketbooker.Util.Enum.RouteStatus;
import com.example.ticketbooker.Util.Mapper.RouteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RouteServiceImp implements RouteService {
    @Autowired
    private RouteRepo routeRepo;

    @Override
    public boolean addRoute(AddRouteDTO dto) {
        try {
            Routes route = RouteMapper.fromAdd(dto);
            this.routeRepo.save(route);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateRoute(UpdateRouteDTO dto) {
        try {
            Routes route = RouteMapper.fromUpdate(dto.getRouteId(), dto);
            this.routeRepo.save(route);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteRoute(RequestRouteIdDTO dto) {
        try {
            this.routeRepo.deleteById(dto.getRouteId());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Routes getRoute(Integer id) {
        Routes route;
        try {
            route = this.routeRepo.findById(id).orElse(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return route;
    }

    @Override
    public ResponseRouteDTO findAllRoutes() {
        ResponseRouteDTO result = new ResponseRouteDTO();
        ArrayList<Routes> routes;
        try{
            routes = this.routeRepo.findAll();
            result.setRouteCount(routes.size());
            result.setList(routes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }

    @Override
    public ResponseRouteDTO findByStatus(RouteStatus status) {
        ResponseRouteDTO result = new ResponseRouteDTO();
        ArrayList<Routes> routes;
        try{
            routes = this.routeRepo.findByStatus(status);
            result.setRouteCount(routes.size());
            result.setList(routes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }

    @Override
    public ResponseRouteDTO findByDepartureLocation(String departureLocation) {
        ResponseRouteDTO result = new ResponseRouteDTO();
        ArrayList<Routes> routes;
        try{
            routes = this.routeRepo.findByDepartureLocation(departureLocation);
            result.setRouteCount(routes.size());
            result.setList(routes);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        System.out.println(result.getRouteCount());
        return result;
    }

    @Override
    public ResponseRouteDTO findByArrivalLocation(String arrivalLocation) {
        ResponseRouteDTO result = new ResponseRouteDTO();
        ArrayList<Routes> routes;
        try{
            routes = this.routeRepo.findByArrivalLocation(arrivalLocation);
            result.setRouteCount(routes.size());
            result.setList(routes);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }

    @Override
    public ResponseRouteDTO findByLocation(String location) {
        ResponseRouteDTO result = new ResponseRouteDTO();
        ArrayList<Routes> routes;
        try{
            routes = this.routeRepo.findByDepartureLocationAndArrivalLocation(location, location);
            result.setRouteCount(routes.size());
            result.setList(routes);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }

    @Override
    public ResponseRouteDTO findByLocations(SearchRouteRequest request) {
        ResponseRouteDTO result = new ResponseRouteDTO();
        ArrayList<Routes> routes;
        try{
            routes = this.routeRepo.findByDepartureLocationAndArrivalLocation(request.getDeparture(), request.getArrival());
            result.setRouteCount(routes.size());
            result.setList(routes);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }
}
