package com.example.ticketbooker.DTO.Routes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchRouteRequest {
    private String departure;
    private String arrival;
}
