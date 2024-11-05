package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Routes.AddRouteDTO;
import com.example.ticketbooker.DTO.Routes.UpdateRouteDTO;
import com.example.ticketbooker.Service.RouteService;
import com.example.ticketbooker.Util.Mapper.RouteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/admin/routes")
public class RouteController {
    @Autowired
    private RouteService routeService;

    @GetMapping
    public String RouteManagement(Model model) {
        model.addAttribute("response", this.routeService.findAllRoutes());
        model.addAttribute("createRouteForm", new AddRouteDTO());
        model.addAttribute("updateRouteForm", new UpdateRouteDTO());
        return "View/Admin/Routes/RouteManagement";
    }

    @GetMapping("/updating/{id}")
    public String userDetails(@PathVariable int id, Model model) {
        UpdateRouteDTO dto = new UpdateRouteDTO();
        if(Objects.nonNull(routeService.getRoute(id))){
            dto = RouteMapper.toUpdateDTO(routeService.getRoute(id));
        }
        model.addAttribute("updateRouteForm", dto);
        return "View/Admin/Routes/UpdateForm";
    }

    @PostMapping("/create")
    public String createRoute(@ModelAttribute("createRouteForm") AddRouteDTO dto, Model model) {
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
        return "redirect:/admin/routes";
    }

    @PostMapping("/update")
    public String updateRoute(@ModelAttribute("updateRouteForm") UpdateRouteDTO dto, Model model) {
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
        return "redirect:/admin/routes";
    }

}
