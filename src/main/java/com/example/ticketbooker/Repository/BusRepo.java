package com.example.ticketbooker.Repository;

import com.example.ticketbooker.Entity.Buses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepo extends JpaRepository<Buses, Integer> {
}
