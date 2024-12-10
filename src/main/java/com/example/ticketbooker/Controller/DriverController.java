package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Driver.DriverDTO;
import com.example.ticketbooker.DTO.Driver.ResponseDriverDTO;
import com.example.ticketbooker.DTO.Driver.UpdateDriverDTO;
import com.example.ticketbooker.Entity.Driver;
import com.example.ticketbooker.Service.DriverService;
import com.example.ticketbooker.DTO.Driver.AddDriverDTO;
import com.example.ticketbooker.Util.Mapper.DriverMapper;
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
@RequestMapping("/admin/drivers")
public class DriverController {
    @Autowired
    private DriverService driverService;
    @GetMapping
    public String driverListPage(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DriverDTO> driverPage = driverService.findAll(pageable);
        model.addAttribute("drivers", driverPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", driverPage.getTotalPages());
        return "View/Admin/Drivers/DriverManagement";
    }
    @GetMapping("/add")
    public String showAddDriver(Model model){
        model.addAttribute("driverDTO", new DriverDTO());
        return "View/Admin/Drivers/DriverDetails";
    }
    @PostMapping ("/add")
    public String CreateDriver(@ModelAttribute ("createDriverForm") AddDriverDTO dto, Model model) {
        try {
            boolean result = driverService.addDriver(dto);
            if (result) {
                model.addAttribute("successMessage", "Successfully created");
            } else {
                model.addAttribute("failedMessage", "User create has failed");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/drivers";
    }
    @GetMapping("/{id}")
    public String showUpdateDriver(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        DriverDTO dto = DriverMapper.toDTO(driverService.getDriver(id));
        if(dto != null){
            model.addAttribute("driverDTO", dto);
            return "View/Admin/Drivers/DriverDetails";
        } else{
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy tài khoản với ID: " + id);
            return "redirect:/admin/drivers";
        }
    }
    @PostMapping ("/update")
    public String UpdateDriver(@ModelAttribute ("updateDriverForm") UpdateDriverDTO dto, Model model) {
        try {
            System.out.println("update dto: "+dto.toString());
            boolean result = driverService.updateDriver(dto);

            if (result) {
                model.addAttribute("successMessage", "Successfully created");
            } else {
                model.addAttribute("failedMessage", "User create has failed");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/drivers";
    }

//    @GetMapping("/{id}")
//    public String driverDetails(@PathVariable Integer id, Model model) {
//        UpdateDriverDTO updateDriverDTO = new UpdateDriverDTO();
//        if(Objects.nonNull(driverService.getDriver(id))){
//            updateDriverDTO = DriverMapper.toUpdateDTO(driverService.getDriver(id));
//        }
//        model.addAttribute("updateDriverForm", updateDriverDTO);
//        return "View/Admin/Drivers/DriverDetails";
//    }
}
