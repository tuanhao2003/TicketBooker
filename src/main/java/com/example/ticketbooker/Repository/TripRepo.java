package com.example.ticketbooker.Repository;

import com.example.ticketbooker.DTO.Trips.SearchTripRequest;
import com.example.ticketbooker.Entity.Trips;
import com.example.ticketbooker.Entity.Routes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
@Repository
public interface TripRepo extends JpaRepository<Trips, Integer> {
    ArrayList<Trips> findAll();
    Trips findById(int id);

    @Query("SELECT t FROM Trips t WHERE " +
            "(t.route = :#{#route}) AND " +
            "(t.departureTime >= :#{#request.departureDate} OR :#{#request.departureDate} IS NULL) AND " +
            "(t.availableSeats >= :#{#request.ticketQuantity})")
    ArrayList<Trips> searchTrip(SearchTripRequest request, Routes route);
    ArrayList<Trips> findAllById(int tripId);

    long countTripsByDepartureTimeBetween (LocalDateTime start, LocalDateTime end);
}
