package com.example.ticketbooker.Repository;

import com.example.ticketbooker.Entity.Buses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepo extends JpaRepository<Buses, Integer> {
    Buses findById(int id);
    List<Buses> findAll();
}

