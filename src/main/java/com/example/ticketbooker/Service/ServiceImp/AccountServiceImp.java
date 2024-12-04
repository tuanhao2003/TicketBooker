package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Account.AccountDTO;
import com.example.ticketbooker.DTO.Users.UpdateUserRequest;
import com.example.ticketbooker.Entity.Account;
import com.example.ticketbooker.Entity.CustomUserDetails;
import com.example.ticketbooker.Entity.Users;
import com.example.ticketbooker.Repository.AccountRepo;
import com.example.ticketbooker.Repository.UserRepo;
import com.example.ticketbooker.Service.AccountService;
import com.example.ticketbooker.Util.Mapper.AccountMapper;
import com.example.ticketbooker.Util.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Primary
@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private UserRepo userRepo;

    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepo.findAll();
        List<AccountDTO> dtos = new ArrayList<>();
        accounts.forEach(account -> dtos.add(AccountMapper.toDTO(account)));
        return dtos;
    }
    //khai
    public AccountDTO getAccountByEmail(String email) {
        Optional<Account> account = accountRepo.findByEmail(email);
        if (account.isEmpty()) {
            System.out.println("Email not found:" + email);
            return null;
        }
        return AccountMapper.toDTO(account.get());
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
    public AccountDTO getAccountByUsername(String username) {
          Optional<Account> account = accountRepo.findByUsername(username);
        if (account.isEmpty()) {
            System.out.println("Username not found:" + username);
            return null;
        }
        return AccountMapper.toDTO(account.get());
    }

    public AccountDTO createAccountWithUser(AccountDTO accountDTO) {
        //Tạo user mới trong database
        Users user = new Users();
        user.setFullName(accountDTO.getUser().getFullName());
        Users savedUser =  userRepo.save(user);

        //Tạo account với user mới vừa tạo
        accountDTO.setUser(savedUser);
        Account account = AccountMapper.toEntity(accountDTO);
        Account result = accountRepo.save(account);
        return AccountMapper.toDTO(result);
    }

    public Account updateAccountWithUser(AccountDTO accountDTO) {
        try {
//            UpdateUserRequest userRequest = UserMapper.toUpdateDTO(accountDTO.getUser());
//            Users updatedUser = userService.updateUser(user);
            Users user = Users.builder()
                    .fullName(accountDTO.getUser().getFullName())
                    .phone(accountDTO.getUser().getPhone())
                    .dateOfBirth(accountDTO.getUser().getDateOfBirth())
                    .userStatus(accountDTO.getUser().getUserStatus())
                    .address(accountDTO.getUser().getAddress())
                    .id(accountDTO.getUser().getId())
                    .gender(accountDTO.getUser().getGender())
                    .profilePhoto(accountDTO.getUser().getProfilePhoto())
                    .build();
            Users updatedUser = userRepo.save(user);
            accountDTO.setUser(updatedUser);
            Account account = AccountMapper.toEntity(accountDTO);
            return this.accountRepo.save(account);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = AccountMapper.toEntity(accountDTO);
        Account savedAccount = accountRepo.save(account);
        return AccountMapper.toDTO(savedAccount);
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

    @Override
    public List<AccountDTO> searchAccounts(String keyword) {
        List<Account> accounts = accountRepo.searchAccounts(keyword);
        return accounts.stream()
                .map(AccountMapper::toDTO)
                .collect(Collectors.toList());
    }
}
