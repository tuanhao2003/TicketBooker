package com.example.ticketbooker.Util.Mapper;

import com.example.ticketbooker.DTO.Account.AccountDTO;
import com.example.ticketbooker.Entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public static AccountDTO toDTO(Account account) {
        return new AccountDTO(
                account.getId(),
                account.getUser(),
                account.getUsername(),
                account.getPassword(),
                account.getEmail(),
                account.getRole(),
                account.getAccountStatus()
        );
    }

    public static Account toEntity(AccountDTO accountDTO) {
        return Account.builder()
                .id(accountDTO.getId())
                .user(accountDTO.getUser())
                .username(accountDTO.getUsername())
                .password(accountDTO.getPassword())
                .email(accountDTO.getEmail())
                .role(accountDTO.getRole())
                .accountStatus(accountDTO.getAccountStatus())
                .build();
    }
}
