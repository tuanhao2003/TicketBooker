package com.example.ticketbooker.Repository;

import com.example.ticketbooker.Entity.Users;
import com.example.ticketbooker.Util.Enum.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    ArrayList<Users> findAll();
    Page<Users> findAll(Pageable pageable);
    ArrayList<Users> findAllByGender(Gender gender);
    ArrayList<Users> findAllByAddress(String address);
    ArrayList<Users> findByFullNameLike(String fullname);
    ArrayList<Users> findAllById(int userId);
}
