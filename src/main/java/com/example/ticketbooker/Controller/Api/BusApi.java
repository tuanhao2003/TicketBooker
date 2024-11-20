package com.example.ticketbooker.Controller.Api;

import com.example.ticketbooker.Service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buses")
public class BusApi {

    @Autowired
    private BusService busService;

    //Xóa bus
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBus(@PathVariable("id") Integer id) {
        if (busService.getBusById(id) != null) {
            busService.deleteBus(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
