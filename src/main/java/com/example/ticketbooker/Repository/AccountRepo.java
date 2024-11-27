package com.example.ticketbooker.Repository;

import com.example.ticketbooker.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
    Optional<Account> findByEmail(String email);
    // Bạn có thể thêm các phương thức tìm kiếm tùy chỉnh ở đây, nếu cần
    Account findById(int id);

    @Query("SELECT a FROM Account a WHERE " +
            "a.username LIKE %:keyword% OR " +
            "a.email LIKE %:keyword% OR " +
            "a.password LIKE %:keyword%")
    List<Account> searchAccounts(@Param("keyword") String keyword);
}
