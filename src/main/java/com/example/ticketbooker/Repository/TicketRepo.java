package com.example.ticketbooker.Repository;

import com.example.ticketbooker.Entity.Tickets;
import com.example.ticketbooker.Util.Enum.TicketStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("ALL")
@Repository
public interface TicketRepo extends JpaRepository<Tickets, Integer> {
    ArrayList<Tickets> findAll();
    ArrayList<Tickets> findAllById(int id);
    List<Tickets> findAllByBookerId(int bookerId);

    @Query("SELECT t FROM Tickets t WHERE t.booker.id = :bookerId " +
            "AND (:ticketId IS NULL OR t.id = :ticketId) " +
            "AND (:departureDate IS NULL OR CAST(t.trip.departureTime AS DATE) = :departureDate) " + // Corrected line
            "AND (:route IS NULL OR CONCAT(t.trip.route.departureLocation, ' - ', t.trip.route.arrivalLocation) LIKE %:route%) " +
            "AND (:status IS NULL OR t.ticketStatus = :status)")
    List<Tickets> searchTickets(@Param("bookerId") int bookerId,
                                @Param("ticketId") Integer ticketId,
                                @Param("departureDate") LocalDate departureDate, // Parameter remains LocalDate
                                @Param("route") String route,
                                @Param("status") TicketStatus status);

    @Query("SELECT COUNT(t) FROM Tickets t WHERE t.invoice.paymentTime BETWEEN :start AND :end")
    int countTicketsByPaymentTimeBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    Page<Tickets> findAllByTripId(int tripId, Pageable pageable);
    // Phương thức mới để trả về toàn bộ danh sách vé mà không phân trang
    List<Tickets> findAllByTripId(int tripId);
}
