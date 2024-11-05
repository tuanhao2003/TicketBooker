package com.example.ticketbooker.Repository;

import com.example.ticketbooker.DTO.Driver.ResponseDriverDTO;
import com.example.ticketbooker.Entity.Driver;
import com.example.ticketbooker.Util.Enum.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
@SuppressWarnings({"SpringDataMethodInconsistencyInspection", "NullableProblems"})
@Repository
public interface DriverRepo  extends JpaRepository<Driver, Integer> {
    ArrayList<Driver> findAll();
    Driver findById(int id);
    ArrayList<Driver> findAllDriversByName(String name);
    ArrayList<Driver> findAllDriversByDriverStatus(DriverStatus status);
    ArrayList<Driver> findAllDriversByPhone(String phone);
    @Query("SELECT d FROM Driver d WHERE " +
            "LOWER(d.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(d.licenseNumber) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(d.phone) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(d.address) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "CAST(d.driverId AS string) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(d.driverStatus) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    ArrayList<Driver> searchDrivers(String searchTerm);

}
