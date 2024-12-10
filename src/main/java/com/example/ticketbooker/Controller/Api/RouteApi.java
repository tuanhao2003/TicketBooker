package com.example.ticketbooker.Controller.Api;

import com.example.ticketbooker.DTO.Routes.RequestRouteIdDTO;
import com.example.ticketbooker.DTO.Routes.ResponseRouteDTO;
import com.example.ticketbooker.DTO.Routes.RouteDTO;
import com.example.ticketbooker.DTO.Routes.UpdateRouteDTO;
import com.example.ticketbooker.Entity.Routes;
import com.example.ticketbooker.Service.RouteService;
import com.example.ticketbooker.Util.Mapper.RouteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/routes")
public class RouteApi {
    @Autowired
    private RouteService routeService;
    @DeleteMapping("/delete")
    public boolean deleteRoute(@RequestBody RequestRouteIdDTO dto) {
        boolean result = false;
        try {
            result = routeService.deleteRoute(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return result;
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateAccountStatus(@PathVariable("id") Integer id, @RequestBody RouteDTO routeDTO) {
        UpdateRouteDTO existingRoute = RouteMapper.toUpdateDTO(routeService.getRoute(id));
        if (existingRoute != null) {
            existingRoute.setStatus(routeDTO.getStatus()); // Update only the status
            routeService.updateRoute(existingRoute);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/search")
    public ResponseRouteDTO searchRouteByLocation(@RequestBody String location) {
        ResponseRouteDTO dto = new ResponseRouteDTO();
        try{
            dto = this.routeService.findByLocation(location);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return dto;
    }

    @GetMapping("/getDepartureLocation")
    public List<String> getDepartureLocation() {
        ResponseRouteDTO responseRoute = routeService.findAllRoutes();
        List<String> departureLocationList = new ArrayList<>();
        responseRoute.getList().forEach(route -> departureLocationList.add(route.getDepartureLocation()));
        return departureLocationList;
    }
    @GetMapping("/getArrivalLocation")
    public List<Routes> getArrivalLocation(@RequestParam String departureLocation) {
        ResponseRouteDTO responseRoute = routeService.findByDepartureLocation(departureLocation);
        return new ArrayList<>(responseRoute.getList());
    }

    @PostMapping("/get-routes")
    public ResponseRouteDTO getRoutes() {
        return routeService.findAllRoutes();
    }
}
