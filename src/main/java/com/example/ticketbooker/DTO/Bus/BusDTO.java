package com.example.ticketbooker.DTO.Bus;

import com.example.ticketbooker.Entity.Routes;
import com.example.ticketbooker.Util.Enum.BusStatus;
import com.example.ticketbooker.Util.Enum.BusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusDTO {
    private Integer id;
    private Routes routeId;
    private String licensePlate;
    private BusType busType;
    private Integer capacity;
    private BusStatus busStatus;
}
