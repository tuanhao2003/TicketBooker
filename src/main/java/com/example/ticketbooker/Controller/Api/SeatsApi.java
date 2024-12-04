package com.example.ticketbooker.Controller.Api;

import com.example.ticketbooker.DTO.Seats.AddSeatDTO;
import com.example.ticketbooker.Service.SeatsService;
import com.example.ticketbooker.Util.Utils.CookieUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

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

//    khi bam nut thanh toan => fetch toi day => ket qua: list parse to string up len cookie ten la seatIds
    @PostMapping("/prebooking-seat")
    public ResponseEntity<String> preBookingSeat(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Lấy dữ liệu từ cookie
            int tripId = Integer.parseInt(CookieUtils.getCookieValue(request, "tripId", "0"));
            String selectedSeats = CookieUtils.getCookieValue(request, "selectedSeats", "");
            String[] seats = selectedSeats.split(",\\s*");

            // Tạo DTO
            AddSeatDTO addSeatDTO = new AddSeatDTO();
            addSeatDTO.setTripId(tripId);
            addSeatDTO.setSeatCode(selectedSeats);

            // Lấy danh sách seatIds từ service
            List<Integer> seatIds = seatsService.addSeats(addSeatDTO);

            // In danh sách seatIds ra console
            System.out.println("Danh sách seatIds được tạo: " + seatIds);
            if (seatIds != null && !seatIds.isEmpty()) {
                // Chuyển danh sách seatIds thành chuỗi
                String seatIdsString = seatIds.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "));
                 System.out.println("Danh sách seatIdsString được tạo: " + seatIdsString);

                String encodedSeatIds = URLEncoder.encode(seatIdsString, StandardCharsets.UTF_8);

                Cookie seatIdsCookie = new Cookie("seatIds", encodedSeatIds);
                seatIdsCookie.setPath("/");
                seatIdsCookie.setMaxAge(900);
                response.addCookie(seatIdsCookie);

                // Trả về kết quả thành công
                return ResponseEntity.ok("Seats pre-booked and added to cookie.");
            } else {
                return ResponseEntity.badRequest().body("No seats were pre-booked.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while pre-booking seats.");
        }
    }

}
