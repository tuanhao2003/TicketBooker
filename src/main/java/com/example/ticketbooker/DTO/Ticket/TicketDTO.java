package com.example.ticketbooker.DTO.Ticket;

import com.example.ticketbooker.Util.Enum.TicketStatus;
import lombok.Data;

@Data
public class TicketDTO {
    private Integer id;
    private Integer tripId;
    private Integer bookerId;
    private Integer invoiceId;
    private String customerName;
    private String customerPhone;
    private Integer seatId;
    private String qrCode;
    private TicketStatus ticketStatus;

    public TicketDTO() {
        this.id = null;
        this.tripId = null;
        this.bookerId = null;
        this.invoiceId = null;
        this.customerName = null;
        this.customerPhone = null;
        this.seatId = null;
        this.qrCode = null;
        this.ticketStatus = null;
    }

    public TicketDTO(Integer id, Integer tripId, Integer bookerId, Integer invoiceId, String customerName, String customerPhone, Integer seatId, String qrCode, TicketStatus ticketStatus) {
        this.id = id;
        this.tripId = tripId;
        this.bookerId = bookerId;
        this.invoiceId = invoiceId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.seatId = seatId;
        this.qrCode = qrCode;
        this.ticketStatus = ticketStatus;
    }
}
