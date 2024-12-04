package com.example.ticketbooker.Controller.Api;

import com.example.ticketbooker.DTO.Ticket.*;
import com.example.ticketbooker.DTO.Users.UserIdRequest;
import com.example.ticketbooker.DTO.Users.UserResponse;
import com.example.ticketbooker.Entity.Tickets;
import com.example.ticketbooker.Service.TicketService;
import com.example.ticketbooker.Util.Enum.TicketStatus;
import com.example.ticketbooker.Util.Mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketApi {

    @Autowired
    private TicketService ticketsService;

    @DeleteMapping("/cancel-ticket")
    public boolean cancelTicket(@RequestBody TicketIdRequest id) {
        boolean result = false;
        try {
            UpdateTicketRequest updated = TicketMapper.toUpdateDTO(TicketMapper.fromResponse(this.ticketsService.getTicketById(id)).get(0));
            updated.setTicketStatus(TicketStatus.CANCELLED);
            result = this.ticketsService.updateTicket(updated);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = false;
        }
        return result;
    }

    @PostMapping("/create-ticket")
    public boolean createTicket(@RequestBody AddTicketRequest request) {
        boolean result = false;
        try {
            result = this.ticketsService.addTicket(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = false;
        }
        return result;
    }

//    @PostMapping("/book-ticket")
//    public boolean bookTicket(@RequestParam int tripId, @RequestParam int bookerId, @RequestParam String tcustomerName, ) {
//        boolean result = false;
//        try {
//            result = this.ticketsService.addTicket(request);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            result = false;
//        }
//        return result;
//    }

    @PostMapping("/payment-infor")
    public ResponseEntity<PaymentInforResponse> paymentInfor(@RequestBody PaymentInforRequest request) {
        try {
            PaymentInforResponse response = ticketsService.getPaymentInfo(request);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
