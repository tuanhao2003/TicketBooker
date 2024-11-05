package com.example.ticketbooker.Util.Mapper;

import com.example.ticketbooker.DTO.Account.AccountDTO;
import com.example.ticketbooker.Entity.Account;

public class AccountMapper {

    public static AccountDTO toDto(Account account) {
        return new AccountDTO(
                account.getId(),
                account.getUsername(),
                account.getEmail(),
                account.getRole(),
                account.getAccountStatus()
        );
    }

    public static Account toEntity(AccountDTO accountDTO) {
        return Account.builder()
                .id(accountDTO.getId())
                .username(accountDTO.getUsername())
                .email(accountDTO.getEmail())
                .role(accountDTO.getRole())
                .accountStatus(accountDTO.getAccountStatus())
                .build();
    }
}
