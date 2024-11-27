package com.example.ticketbooker.Repository;

import com.example.ticketbooker.DTO.Trips.SearchTripRequest;
import com.example.ticketbooker.Entity.Trips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface TripRepo extends JpaRepository<Trips, Integer> {
    ArrayList<Trips> findAll();
    Trips findById(int id);

    @Query("SELECT t FROM Trips t WHERE " +
            "(t.departureStation = :#{#request.departure} OR :#{#request.departure} IS NULL) AND " +
            "(t.arrivalStation = :#{#request.arrival} OR :#{#request.arrival} IS NULL) AND " +
            "(t.departureTime >= :#{#request.departureDate} OR :#{#request.departureDate} IS NULL) AND " +
            "(t.arrivalTime <= :#{#request.arrivalDate} OR :#{#request.arrivalDate} IS NULL) AND " +
            "(t.availableSeats >= :#{#request.ticketQuantity})")
    ArrayList<Trips> searchTrip(SearchTripRequest request);
}
