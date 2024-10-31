package com.example.ticketbooker.DTO.Ticket;

import lombok.Data;

@Data
public class TicketBookingRequestDTO {
    private Integer tripId;
    private String customerName;
    private String customerPhone;
    private Integer seatId;
    private Integer bookerId; // account ID của người đặt vé

    public TicketBookingRequestDTO() {
        this.tripId = null;
        this.customerName = null;
        this.customerPhone = null;
        this.seatId = null;
        this.bookerId = null;
    }

    public TicketBookingRequestDTO(Integer tripId, String customerName, String customerPhone, Integer seatId, Integer bookerId) {
        this.tripId = tripId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.seatId = seatId;
        this.bookerId = bookerId;
    }
}
