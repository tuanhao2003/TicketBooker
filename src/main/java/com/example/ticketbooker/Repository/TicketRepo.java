package com.example.ticketbooker.Repository;

import com.example.ticketbooker.Entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@SuppressWarnings("ALL")
@Repository
public interface TicketRepo extends JpaRepository<Tickets, Integer> {
    ArrayList<Tickets> findAll();
    ArrayList<Tickets> findAllById(int id);
}
