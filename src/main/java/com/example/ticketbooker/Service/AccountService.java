package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Account.AccountDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAllAccounts();
    AccountDTO getAccountById(int id);
    AccountDTO createAccount(AccountDTO accountDTO);
    boolean updateAccount(AccountDTO accountDTO);
    void deleteAccount(Integer id);
}
