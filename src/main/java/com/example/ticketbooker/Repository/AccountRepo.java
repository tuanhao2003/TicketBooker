package com.example.ticketbooker.Repository;

import com.example.ticketbooker.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {
    // Bạn có thể thêm các phương thức tìm kiếm tùy chỉnh ở đây, nếu cần
}
