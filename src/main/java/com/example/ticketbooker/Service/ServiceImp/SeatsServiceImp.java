package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Seats.AddSeatDTO;
import com.example.ticketbooker.Entity.Seats;
import com.example.ticketbooker.Entity.Trips;
import com.example.ticketbooker.Util.Mapper.SeatsMapper;
import com.example.ticketbooker.Repository.SeatsRepo;
import com.example.ticketbooker.Service.SeatsService;
import com.example.ticketbooker.Service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SeatsServiceImp implements SeatsService {

    @Autowired
    private SeatsRepo seatsRepository;

    @Autowired
    private SeatsMapper seatsMapper;

    @Autowired
    private TripService tripService;

    @Override
    public List<Integer> addSeats(AddSeatDTO addSeatDTO) {
        List<Integer> seatIds = new ArrayList<>(); // Danh sách để chứa tất cả các seatId

        try {
            // Lấy thông tin trip từ tripId
            Trips trip = tripService.getTripById(addSeatDTO.getTripId());
            if (trip == null) {
                throw new IllegalArgumentException("Trip ID không tồn tại");
            }

            // Tách các mã ghế từ chuỗi
            String[] seatCodes = addSeatDTO.getSeatCode().split(",\\s*");

            // Lưu ghế vào cơ sở dữ liệu và lấy seatId
            for (String seatCode : seatCodes) {
                Seats seat = Seats.builder()
                        .trip(trip)
                        .seatCode(seatCode)
                        .build();

                Seats savedSeat = seatsRepository.save(seat); // Lưu ghế vào DB và lấy ghế đã lưu
                seatIds.add(savedSeat.getId()); // Thêm seatId vào danh sách
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return seatIds; // Trả về danh sách seatId đã lưu
    }
}
