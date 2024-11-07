package com.example.ticketbooker.Repository;

import com.example.ticketbooker.Entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepo extends JpaRepository<Tickets, Integer> {
    List<Tickets> findAll();
    Tickets findById(int id);
}
