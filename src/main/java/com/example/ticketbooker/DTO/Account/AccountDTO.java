package com.example.ticketbooker.DTO.Account;

import com.example.ticketbooker.Entity.Users;
import com.example.ticketbooker.Util.Enum.AccountStatus;
import com.example.ticketbooker.Util.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {
    private Integer id;
    private Users user;
    private String username;
    private String password;
    private String email;
    private Role role;
    private AccountStatus accountStatus;
}
