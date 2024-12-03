package com.example.ticketbooker.DTO.Seats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddSeatDTO {
    private Integer tripId; // ID của chuyến đi (Trip)
    private String seatCode; // Mã ghế
}
