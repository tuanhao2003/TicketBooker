package com.example.ticketbooker.Repository;

import com.example.ticketbooker.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
    Optional<Account> findByEmail(String email);
    // Bạn có thể thêm các phương thức tìm kiếm tùy chỉnh ở đây, nếu cần
}
