package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Routes.AddRouteDTO;
import com.example.ticketbooker.DTO.Routes.UpdateRouteDTO;
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
    public boolean deleteRoute(int id) {
        try {
            this.routeRepo.deleteById(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Routes getRoute(int id) {
        Routes route;
        try {
            route = this.routeRepo.findById(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return route;
    }

    @Override
    public ArrayList<Routes> findAllRoutes() {
        ArrayList<Routes> routes;
        try{
            routes = this.routeRepo.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return routes;
    }

    @Override
    public ArrayList<Routes> findByStatus(RouteStatus status) {
        ArrayList<Routes> routes;
        try{
            routes = this.routeRepo.findByRouteStatus(status);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return routes;
    }

    @Override
    public ArrayList<Routes> findByDepartureLocation(String departureLocation) {
        ArrayList<Routes> routes;
        try{
            routes = this.routeRepo.findByDepartureLocation(departureLocation);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return routes;
    }

    @Override
    public ArrayList<Routes> findByArrivalLocation(String arrivalLocation) {
        ArrayList<Routes> routes;
        try{
            routes = this.routeRepo.findByArrivalLocation(arrivalLocation);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return routes;
    }
}
