package com.example.ticketbooker.Repository;

import com.example.ticketbooker.Entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Tickets, Integer> {
}
