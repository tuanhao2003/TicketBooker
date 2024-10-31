package com.example.ticketbooker.DTO.Ticket;

import lombok.Data;

@Data
public class TicketResponseDTO {
    private Integer ticketId;
    private String tripDetails;
    private String seatCode;
    private String customerName;
    private String customerPhone;
    private String qrCode;
    private String ticketStatus;

    public TicketResponseDTO() {
        this.ticketId = null;
        this.tripDetails = null;
        this.seatCode = null;
        this.customerName = null;
        this.customerPhone = null;
        this.qrCode = null;
        this.ticketStatus = null;
    }

    public TicketResponseDTO(Integer ticketId, String tripDetails, String seatCode, String customerName, String customerPhone, String qrCode, String ticketStatus) {
        this.ticketId = ticketId;
        this.tripDetails = tripDetails;
        this.seatCode = seatCode;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.qrCode = qrCode;
        this.ticketStatus = ticketStatus;
    }
}
