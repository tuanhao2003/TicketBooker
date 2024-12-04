package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Routes.SearchRouteRequest;
import com.example.ticketbooker.DTO.Trips.*;
import com.example.ticketbooker.Entity.Routes;
import com.example.ticketbooker.Entity.Trips;
import com.example.ticketbooker.Repository.TripRepo;
import com.example.ticketbooker.Service.RouteService;
import com.example.ticketbooker.Service.TripService;
import com.example.ticketbooker.Util.Mapper.TripMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImp implements TripService {
    @Autowired
    private TripRepo tripRepo;
    @Autowired
    private RouteService routeService;

    @Override
    public ResponseTripDTO getTripById(int tripId) {
        ResponseTripDTO result = new ResponseTripDTO();
        try {
            result = TripMapper.toResponseDTO(this.tripRepo.findAllById(tripId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }

    @Override
    public ResponseTripDTO getAllTrips() {
        ResponseTripDTO result = new ResponseTripDTO();
        try {
            result = TripMapper.toResponseDTO(this.tripRepo.findAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }

    @Override
    public Page<TripDTO> getAllTrips(Pageable pageable) {
        Page<Trips> trips = tripRepo.findAll(pageable);
        return trips.map(TripMapper::toDTO);
    }

    @Override
    public boolean addTrip(AddTripDTO dto) {
        try {
            Trips trip = TripMapper.fromAdd(dto);
            this.tripRepo.save(trip);
        } catch (Exception e) {
            System.out.println("Error adding trip: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateTrip(UpdateTripDTO updateTripDTO) {
        try {
            Trips existingTrip = tripRepo.findById(updateTripDTO.getTripId()).orElse(null);
            if (existingTrip != null) {
                Trips updatedTrip = TripMapper.fromUpdate(updateTripDTO);
                tripRepo.save(updatedTrip);
                return true; // Cập nhật thành công
            }
            return false; // Không tìm thấy chuyến đi
        } catch (Exception e) {
            System.out.println("Error updating trip: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteTrip(RequestIdTripDTO dto) {
        try {
            this.tripRepo.deleteById(dto.getTripId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Trips getTripById(Integer tripId) {
        return tripRepo.findById(tripId).orElse(null);
    }

    @Override
    public ResponseTripDTO searchTrip(SearchTripRequest dto) {
        ResponseTripDTO result = new ResponseTripDTO();
        try {
            Routes route = routeService.findByLocations(new SearchRouteRequest(dto.getDeparture(), dto.getArrival())).getList().get(0);
            result = TripMapper.toResponseDTO(this.tripRepo.searchTrip(dto, route));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }

    public Trips getTripByIdpath(int tripId) {
        return tripRepo.findById(tripId);
    }
}
