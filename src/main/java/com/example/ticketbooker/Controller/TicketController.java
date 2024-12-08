package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Ticket.PaymentInforRequest;
import com.example.ticketbooker.DTO.Ticket.TicketIdRequest;
import com.example.ticketbooker.DTO.Ticket.TicketResponse;
import com.example.ticketbooker.DTO.Ticket.UpdateTicketRequest;
import com.example.ticketbooker.DTO.Trips.ResponseTripDTO;
import com.example.ticketbooker.Entity.Trips;
import com.example.ticketbooker.Service.TicketService;
import com.example.ticketbooker.Service.TripService;
import com.example.ticketbooker.Util.Mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
@RequestMapping("/admin/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private TripService tripService;

    @GetMapping
    public String allTickets(Model model, @PageableDefault(size = 10) Pageable pageable, @RequestParam(value = "tripId", required = false) Integer tripId) {
        ResponseTripDTO responseTripDTO = tripService.getAllTrips();
        List<Trips> trips = responseTripDTO.getListTrips();
        TicketResponse ticketResponse;

        if (tripId != null) {
            ticketResponse = ticketService.getTicketsByTripId(tripId, pageable); // Lọc theo chuyến
        } else {
            ticketResponse = ticketService.getAllTickets(pageable); // Hiển thị tất cả vé
        }

        model.addAttribute("trips", trips);
        model.addAttribute("ticketResponse", ticketResponse);
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

    @GetMapping("/export/excel")
    public ResponseEntity<InputStreamResource> exportTicketsToExcel(@RequestParam(value = "tripId", required = false) Integer tripId) {
        ByteArrayInputStream in;

        // Kiểm tra xem có chọn chuyến không, nếu không sẽ lấy toàn bộ vé
        if (tripId != null) {
            in = ticketService.exportTicketsToExcelByTripId(tripId);
        } else {
            in = ticketService.exportAllTicketsToExcel(); // Thêm phương thức mới để xuất toàn bộ vé
        }

        // Đặt tên file
        String fileName = "tickets.xlsx";
        if (tripId != null) {
            fileName = "tickets_trip_" + tripId + ".xlsx";
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + fileName); // Câu lệnh này sẽ yêu cầu trình duyệt mở hộp thoại lưu file

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(in));
    }

}
