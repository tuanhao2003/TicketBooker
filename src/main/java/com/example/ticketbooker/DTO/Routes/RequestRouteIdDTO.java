package com.example.ticketbooker.DTO.Routes;

import lombok.Data;

@Data
public class RequestRouteIdDTO {
    Integer routeId;
    public RequestRouteIdDTO() {
        this.routeId = null;
    }
    public RequestRouteIdDTO(Integer routeId) {
        this.routeId = routeId;
    }
}
