package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Driver.DriverDTO;
import com.example.ticketbooker.DTO.Routes.AddRouteDTO;
import com.example.ticketbooker.DTO.Routes.RouteDTO;
import com.example.ticketbooker.DTO.Routes.UpdateRouteDTO;
import com.example.ticketbooker.Service.RouteService;
import com.example.ticketbooker.Util.Mapper.DriverMapper;
import com.example.ticketbooker.Util.Mapper.RouteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequestMapping("/admin/routes")
public class RouteController {
    @Autowired
    private RouteService routeService;

    @GetMapping
    public String RouteManagement(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<RouteDTO> routePage = routeService.findAllRoutes(pageable);
        model.addAttribute("routes", routePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", routePage.getTotalPages());
        return "View/Admin/Routes/RouteManagement";
    }

    @GetMapping("/add")
    public String AddRoute(Model model) {
        model.addAttribute("routeDTO", new RouteDTO());
        return "View/Admin/Routes/UpdateForm";
    }
    @PostMapping("/add")
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

    @GetMapping("/{id}")
    public String routeDetails(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        RouteDTO dto = RouteMapper.toDTO(routeService.getRoute(id));
        if(dto != null){
            model.addAttribute("routeDTO", dto);
            return "View/Admin/Routes/UpdateForm";
        } else{
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy tuyến đường với ID: " + id);
            return "redirect:/admin/routes";
        }
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
