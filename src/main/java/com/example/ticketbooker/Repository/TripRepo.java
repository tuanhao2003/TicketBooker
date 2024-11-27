package com.example.ticketbooker.Repository;

import com.example.ticketbooker.Entity.Trips;
import com.example.ticketbooker.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface TripRepo extends JpaRepository<Trips, Integer> {
    ArrayList<Trips> findAll();
    Trips findById(int id);
    ArrayList<Trips> findAllById(int tripId);

}
