package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Account.AccountDTO;
import com.example.ticketbooker.Entity.Account;
import com.example.ticketbooker.Repository.AccountRepo;
import com.example.ticketbooker.Util.Mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    public List<AccountDTO> findAll() {
        return accountRepo.findAll().stream()
                .map(AccountMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<AccountDTO> findById(Integer id) {
        return accountRepo.findById(id)
                .map(AccountMapper::toDto);
    }

    public AccountDTO save(AccountDTO accountDTO) {
        Account account = AccountMapper.toEntity(accountDTO);
        Account savedAccount = accountRepo.save(account);
        return AccountMapper.toDto(savedAccount);
    }

    public void delete(Integer id) {
        accountRepo.deleteById(id);
    }

    //Khải
    public boolean authenticate(String username, String password) {
        Optional<Account> optionalAccount = accountRepo.findByUsername(username);
        if (optionalAccount.isEmpty()) {
            optionalAccount = accountRepo.findByEmail(username);
            if (optionalAccount.isEmpty()) {
                return false;
            }
        }
        Account account = optionalAccount.get();
        return account.getPassword().equals(password);
    }
}
