package com.example.ticketbooker.Repository;

import com.example.ticketbooker.Entity.Driver;
import com.example.ticketbooker.Util.Enum.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
@SuppressWarnings({"SpringDataMethodInconsistencyInspection", "NullableProblems"})
@Repository
public interface DriverRepo  extends JpaRepository<Driver, Integer> {
    ArrayList<Driver> findAll();
    Driver findById(int id);
    ArrayList<Driver> findDriverByName(String name);
    ArrayList<Driver> findDriversByStatus(DriverStatus status);
}
