package com.example.ticketbooker.Repository;

import com.example.ticketbooker.Entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("ALL")
@Repository
public interface TicketRepo extends JpaRepository<Tickets, Integer> {
    ArrayList<Tickets> findAll();
    ArrayList<Tickets> findAllById(int id);
    List<Tickets> findAllByBookerId(int bookerId);
}
