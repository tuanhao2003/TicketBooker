package com.example.ticketbooker.Repository;

import com.example.ticketbooker.Entity.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoices, Integer> {
}
