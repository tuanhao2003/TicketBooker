package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Account.AccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAllAccounts();
    AccountDTO getAccountById(int id);
    AccountDTO getAccountByEmail(String email);
    AccountDTO createAccount(AccountDTO accountDTO);
    boolean updateAccount(AccountDTO accountDTO);
    void deleteAccount(Integer id);
    Page<AccountDTO> getAllAccounts(Pageable pageable);
}
