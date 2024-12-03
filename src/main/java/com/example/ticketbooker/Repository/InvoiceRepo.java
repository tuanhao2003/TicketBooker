package com.example.ticketbooker.Repository;

import com.example.ticketbooker.Entity.Invoices;
import com.example.ticketbooker.Entity.Users;
import com.example.ticketbooker.Util.Enum.Gender;
import com.example.ticketbooker.Util.Enum.PaymentMethod;
import com.example.ticketbooker.Util.Enum.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoices, Integer> {
    ArrayList<Invoices> findAll();
    @Query("SELECT i FROM Invoices i WHERE " +
            "(:totalAmount IS NULL OR i.totalAmount = :totalAmount) AND " +
            "(:paymentStatus IS NULL OR i.paymentStatus = :paymentStatus) AND " +
            "(:paymentMethod IS NULL OR i.paymentMethod = :paymentMethod)")
    ArrayList<Invoices> searchInvoices(
            @Param("totalAmount") Integer totalAmount,
            @Param("paymentStatus") PaymentStatus paymentStatus,
            @Param("paymentMethod") PaymentMethod paymentMethod);

    List<Invoices> findAllByPaymentTimeBetweenAndPaymentStatus(LocalDateTime start, LocalDateTime end, PaymentStatus paymentStatus);
}
