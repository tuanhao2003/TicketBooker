package com.example.ticketbooker.Repository;

import com.example.ticketbooker.Entity.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SeatsRepo extends JpaRepository<Seats, Integer> {
}
