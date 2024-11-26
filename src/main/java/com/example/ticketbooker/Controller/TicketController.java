package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Ticket.TicketDTO;
import com.example.ticketbooker.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Controller
@RequestMapping("/admin/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    //Hiển thị danh sách tickets
    @GetMapping()
    public String listTickets(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<TicketDTO> ticketPage = ticketService.getAllTickets(pageable);

        model.addAttribute("tickets", ticketPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", ticketPage.getTotalPages());

        return "View/Admin/TicketManagement/Ticket";
    }

    //Hiển thị form thêm ticket
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("ticketDTO", new TicketDTO());
        return "View/Admin/TicketManagement/TicketForm";
    }

    // Xử lý thêm ticket
    @PostMapping("/add")
    public String addTicket(@ModelAttribute("ticketDTO") TicketDTO ticketDTO, RedirectAttributes redirectAttributes) {
        ticketService.createTicket(ticketDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm vé thành công!");
        return "redirect:/admin/tickets";
    }

    // Hiển thị form sửa ticket
    @GetMapping("/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        TicketDTO ticketDTO = ticketService.getTicketById(id);
        if (ticketDTO != null) {
            model.addAttribute("ticketDTO", ticketDTO);
            return "View/Admin/TicketManagement/TicketForm";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy vé với ID: " + id);
            return "redirect:/admin/tickets";
        }
    }

    // Xử lý sửa ticket
    @PostMapping("/update")
    public String updateTicket(@ModelAttribute("ticketDTO") TicketDTO ticketDTO, RedirectAttributes redirectAttributes) {
        boolean updatedTicket = ticketService.updateTicket(ticketDTO);
        if (updatedTicket) {
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật vé thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Cập nhật vé thất bại!");
        }
        return "redirect:/admin/tickets";
    }

}
