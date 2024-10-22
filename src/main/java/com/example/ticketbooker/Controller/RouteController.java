package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Routes.AddRouteDTO;
import com.example.ticketbooker.DTO.Routes.UpdateRouteDTO;
import com.example.ticketbooker.Entity.Routes;
import com.example.ticketbooker.Service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("admin/RouteManagement")
public class RouteController {
    @Autowired
    private RouteService routeService;

    @GetMapping
    public String RouteManagement(Model model) {
        ArrayList<Routes> routes = this.routeService.findAllRoutes();
        model.addAttribute("routes", routes);
//        return "View/Admin/RouteManagement";
        return "View/User/AllRoutes";
    }


    @PostMapping("/create")
    public String createRoute(@ModelAttribute("routeAddForm") AddRouteDTO dto, Model model) {
        try{
            boolean result = this.routeService.addRoute(dto);
            if(result) {
                model.addAttribute("successMessage", "Route created successfully");
            }else{
                model.addAttribute("errorMessage", "Action failed");
            }
        }catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:admin/RouteManagement";
    }

    @PostMapping("/update")
    public String updateRoute(@ModelAttribute("routeUpdateForm") UpdateRouteDTO dto, Model model) {
        try{
            boolean result = this.routeService.updateRoute(dto);
            if(result) {
                model.addAttribute("successMessage", "Route updated successfully");
            }else{
                model.addAttribute("errorMessage", "Action failed");
            }
        }catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:admin/RouteManagement";
    }

}
