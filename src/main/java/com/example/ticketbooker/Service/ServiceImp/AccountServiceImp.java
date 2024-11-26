package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Account.AccountDTO;
import com.example.ticketbooker.Entity.Account;
import com.example.ticketbooker.Repository.AccountRepo;
import com.example.ticketbooker.Service.AccountService;
import com.example.ticketbooker.Util.Mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepo.findAll();
        List<AccountDTO> dtos = new ArrayList<>();
        accounts.forEach(account -> dtos.add(AccountMapper.toDTO(account)));
        return dtos;
    }

    @Override
    public Page<AccountDTO> getAllAccounts(Pageable pageable) {
        Page<Account> accounts = accountRepo.findAll(pageable);
        return accounts.map(AccountMapper::toDTO);
    }

    @Override
    public AccountDTO getAccountById(int id) {
        AccountDTO result = new AccountDTO();
        try {
            result = AccountMapper.toDTO(this.accountRepo.findById(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = AccountMapper.toEntity(accountDTO);
        Account savedAccount = accountRepo.save(account);
        return AccountMapper.toDTO(accountRepo.save(account));
    }

    @Override
    public boolean updateAccount(AccountDTO accountDTO) {
        try {
            Account account = AccountMapper.toEntity(accountDTO);
            this.accountRepo.save(account);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void deleteAccount(Integer id) {
        accountRepo.deleteById(id);
    }
}
