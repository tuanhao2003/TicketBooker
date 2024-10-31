package com.example.ticketbooker.DTO.Routes;

import com.example.ticketbooker.Entity.Routes;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class ResponseRouteDTO {
    private int routeCount;
    private ArrayList<Routes> list;
    public ResponseRouteDTO() {
        this.routeCount = 0;
        this.list = new ArrayList<>();
    }
    public ResponseRouteDTO(int routeCount, ArrayList<Routes> list) {
        this.routeCount = routeCount;
        this.list = list;
    }
}
