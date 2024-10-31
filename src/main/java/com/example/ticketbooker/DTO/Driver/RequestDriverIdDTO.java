package com.example.ticketbooker.DTO.Driver;

import lombok.Data;

@Data
public class RequestDriverIdDTO {
    Integer driverId;
    public RequestDriverIdDTO() {
        driverId = null;
    }
    public RequestDriverIdDTO(Integer driverId) {
        this.driverId = driverId;
    }

}
