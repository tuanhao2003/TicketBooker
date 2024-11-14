package com.example.ticketbooker.Repository;

import com.example.ticketbooker.Entity.Invoices;
import com.example.ticketbooker.Entity.Users;
import com.example.ticketbooker.Util.Enum.Gender;
import com.example.ticketbooker.Util.Enum.PaymentMethod;
import com.example.ticketbooker.Util.Enum.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoices, Integer> {
    ArrayList<Invoices> findAll();
    ArrayList<Invoices> findAllByPaymentStatus(PaymentStatus paymentStatus);
    ArrayList<Invoices> findAllByPaymentMethod(PaymentMethod paymentMethod);

}
