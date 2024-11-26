package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Bus.BusDTO;
import com.example.ticketbooker.Service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/buses")
public class BusController {

    @Autowired
    private BusService busService;

    //Hiển thị danh sách buses
    @GetMapping()
    public String listBuses(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<BusDTO> busPage = busService.getAllBuses(pageable);

        model.addAttribute("buses", busPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", busPage.getTotalPages());

        return "View/Admin/Bus/Bus";
    }

    //Hiển thị form thêm bus
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("busDTO", new BusDTO());
        return "View/Admin/Bus/BusForm";
    }

    // Xử lý thêm bus
    @PostMapping("/add")
    public String addBus(@ModelAttribute("busDTO") BusDTO busDTO, RedirectAttributes redirectAttributes) {
        busService.createBus(busDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm xe thành công!");
        return "redirect:/admin/buses";
    }

    // Hiển thị form sửa bus
    @GetMapping("/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        BusDTO busDTO = busService.getBusById(id);
        if (busDTO != null) {
            model.addAttribute("busDTO", busDTO);
            return "View/Admin/Bus/BusForm";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy xe với ID: " + id);
            return "redirect:/admin/buses";
        }
    }

    // Xử lý sửa bus
    @PostMapping("/update")
    public String updateBus(@ModelAttribute("busDTO") BusDTO busDTO, RedirectAttributes redirectAttributes) {
        boolean updatedBus = busService.updateBus(busDTO);
        if (updatedBus) {
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật xe thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Cập nhật xe thất bại!");
        }
        return "redirect:/admin/buses";
    }
}
