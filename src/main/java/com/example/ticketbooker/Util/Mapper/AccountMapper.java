package com.example.ticketbooker.Util.Mapper;

import com.example.ticketbooker.DTO.Account.AccountDTO;
import com.example.ticketbooker.Entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public static AccountDTO toDTO(Account account) {
        return AccountDTO.builder()
                .id(account.getId())
                .userId(account.getUser()) // Mapping Users entity
                .username(account.getUsername())
                .password(account.getPassword())
                .email(account.getEmail())
                .role(account.getRole())
                .accountStatus(account.getAccountStatus())
                .build();
    }

    public static Account toEntity(AccountDTO accountDTO) {
        return Account.builder()
                .id(accountDTO.getId())
                .user(accountDTO.getUserId()) // Mapping Users entity from DTO
                .username(accountDTO.getUsername())
                .password(accountDTO.getPassword())
                .email(accountDTO.getEmail())
                .role(accountDTO.getRole())
                .accountStatus(accountDTO.getAccountStatus())
                .build();
    }
}
