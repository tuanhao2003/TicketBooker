package com.example.ticketbooker.Repository;

import com.example.ticketbooker.DTO.ChartData;
import com.example.ticketbooker.Entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface StatisticRepo extends JpaRepository<Tickets,Long> {
    @Query(value = "SELECT DATE(i.paymentTime) AS paymentDate, " +
            "COUNT(DISTINCT t.invoiceId) AS totalTickets, " +
            "COUNT(i.invoiceId) AS totalInvoices, " +
            "SUM(i.totalAmount) AS totalRevenue " +
            "FROM Tickets t " +
            "JOIN Invoices i ON t.invoiceId = i.invoiceId " +
            "WHERE DATE(i.paymentTime) BETWEEN :startDate AND :endDate " +
            "GROUP BY DATE(i.paymentTime) " +
            "ORDER BY DATE(i.paymentTime)", nativeQuery = true)
    List<Object[]> fetchStatistics(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    @Query("SELECT COUNT(*) from Users")
    int countAllUser();

}
