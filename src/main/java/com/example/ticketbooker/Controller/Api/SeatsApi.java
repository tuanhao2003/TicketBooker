package com.example.ticketbooker.Controller.Api;

import com.example.ticketbooker.DTO.Seats.AddSeatDTO;
import com.example.ticketbooker.Service.SeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatsApi {

    @Autowired
    private SeatsService seatsService;

    @PostMapping("/add")
    public ResponseEntity<List<Integer>> addSeats(@RequestBody AddSeatDTO addSeatDTO) {
        try {
            // Gọi service để thêm ghế và nhận danh sách seatId
            List<Integer> seatIds = seatsService.addSeats(addSeatDTO);

            // Trả về danh sách seatId sau khi ghế được thêm thành công
            return ResponseEntity.ok(seatIds);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
