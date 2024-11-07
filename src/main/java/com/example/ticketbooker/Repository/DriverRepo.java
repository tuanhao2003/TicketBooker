package com.example.ticketbooker.Repository;

import com.example.ticketbooker.DTO.Driver.ResponseDriverDTO;
import com.example.ticketbooker.Entity.Driver;
import com.example.ticketbooker.Util.Enum.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query(
            value = "SELECT * FROM Driver d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', ?1 , '%')) OR " +
            "LOWER(d.licenseNumber) LIKE LOWER(CONCAT('%', ?1, '%')) OR " +
            "LOWER(d.phone) LIKE LOWER(CONCAT('%', ?1, '%')) OR " +
            "LOWER(d.address) LIKE LOWER(CONCAT('%', ?1, '%')) OR " +
            "CAST(d.driverId AS CHAR) LIKE LOWER(CONCAT('%', ?1, '%')) OR " +
            "LOWER(d.driverStatus) LIKE LOWER(CONCAT('%', ?1, '%'))",
            nativeQuery = true)
    ArrayList<Driver> searchDrivers(String searchTerm);

}
