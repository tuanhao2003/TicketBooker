package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Ticket.PaymentInforRequest;
import com.example.ticketbooker.DTO.Ticket.TicketIdRequest;
import com.example.ticketbooker.DTO.Ticket.UpdateTicketRequest;
import com.example.ticketbooker.Service.TicketService;
import com.example.ticketbooker.Util.Mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    
    @GetMapping
    public String allTickets(Model model) {
        model.addAttribute("ticketResponse", ticketService.getAllTickets());
        return "View/Admin/Tickets/ListTicket";
    }

    @GetMapping("/details/{id}")
    public String ticketDetails(@PathVariable int id, Model model) {
        UpdateTicketRequest updateRequest = new UpdateTicketRequest();
        TicketIdRequest ticketId = new TicketIdRequest(id);
        if(ticketService.getTicketById(ticketId).getTicketsCount() == 1){
            updateRequest = TicketMapper.toUpdateDTO(ticketService.getTicketById(ticketId).getListTickets().get(0));
        }
        model.addAttribute("updateUserForm", updateRequest);
        return "View/Admin/Tickets/TicketDetails";
    }
}
