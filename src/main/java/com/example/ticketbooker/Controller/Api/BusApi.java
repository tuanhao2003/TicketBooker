package com.example.ticketbooker.Controller.Api;

import com.example.ticketbooker.DTO.Bus.BusDTO;
import com.example.ticketbooker.Entity.Buses;
import com.example.ticketbooker.Repository.BusRepo;
import com.example.ticketbooker.Service.BusService;
import com.example.ticketbooker.Util.Enum.BusStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/buses")
public class BusApi {

    @Autowired
    private BusService busService;

    @Autowired
    private BusRepo busRepository;

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

    //Cập nhật trạng thái của bus
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateBusStatus(@PathVariable("id") Integer id, @RequestBody BusDTO busDTO) {
        BusDTO existingBus = busService.getBusById(id);
        if (existingBus != null) {
            existingBus.setBusStatus(busDTO.getBusStatus()); // Update only the status
            busService.updateBus(existingBus);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/byLicensePlate/{licensePlate}")
    public ResponseEntity<?> getBusIdByLicensePlate(@PathVariable("licensePlate") String licensePlate) {
        Optional<Buses> busOptional = busRepository.findByLicensePlate(licensePlate); // Use the repository method directly. More efficient.

        if (busOptional.isPresent()) {
            return ResponseEntity.ok(busOptional.get().getId()); // Return the busId
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if not found
        }
    }
}
