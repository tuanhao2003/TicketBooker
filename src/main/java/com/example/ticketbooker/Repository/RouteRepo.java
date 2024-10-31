package com.example.ticketbooker.Repository;

import com.example.ticketbooker.Entity.Routes;
import com.example.ticketbooker.Util.Enum.RouteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RouteRepo extends JpaRepository<Routes, Integer> {
    Routes findById(int id);
    ArrayList<Routes> findAll();
    ArrayList<Routes> findByStatus(RouteStatus status);
    ArrayList<Routes> findByDepartureLocation(String departureLocation);
    ArrayList<Routes> findByArrivalLocation(String arrivalLocation);
    ArrayList<Routes> findByDepartureLocationAndArrivalLocation(String departureLocation, String arrivalLocation);

}
