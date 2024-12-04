package com.example.ticketbooker.DTO.Trips;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTripByIdDTO {

    private String departureLocation;
    private String arrivalLocation;
    private String departureTime;
    private String totalPrice;
}
