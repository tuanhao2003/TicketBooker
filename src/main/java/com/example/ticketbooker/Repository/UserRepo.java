package com.example.ticketbooker.Repository;

import com.example.ticketbooker.Entity.Users;
import com.example.ticketbooker.Util.Enum.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@SuppressWarnings({"SpringDataMethodInconsistencyInspection", "NullableProblems"})
@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    ArrayList<Users> findAll();
    Users findById(int id);
    ArrayList<Users> findAllUsersByGender(Gender gender);
    ArrayList<Users> findAllUserByAddress(String address);
    ArrayList<Users> findAllByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}
