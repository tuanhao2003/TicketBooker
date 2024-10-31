package com.example.ticketbooker.Repository;

import com.example.ticketbooker.DTO.Driver.ResponseDriverDTO;
import com.example.ticketbooker.Entity.Driver;
import com.example.ticketbooker.Util.Enum.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
@SuppressWarnings({"SpringDataMethodInconsistencyInspection", "NullableProblems"})
@Repository
public interface DriverRepo  extends JpaRepository<Driver, Integer> {
    ArrayList<Driver> findAll();
    ArrayList<Driver> findById(int id);
    ArrayList<Driver> findAllDriversByName(String name);
    ArrayList<Driver> findAllDriversByDriverStatus(DriverStatus status);
    ArrayList<Driver> findAllDriversByPhone(String phone);

}
