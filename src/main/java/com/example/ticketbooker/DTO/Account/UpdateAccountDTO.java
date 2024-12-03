package com.example.ticketbooker.DTO.Account;

import com.example.ticketbooker.Entity.Users;
import com.example.ticketbooker.Util.Enum.AccountStatus;
import com.example.ticketbooker.Util.Enum.Role;
import lombok.Data;

@Data
public class UpdateAccountDTO {
    private Integer accountId;
    private String username;
    private Integer userId;
    private String email;
    private Role role;
    private AccountStatus accountStatus;

    public UpdateAccountDTO() {
        this.accountId = null;
        this.username = "";
        this.email = "";
        this.role = null;
        this.accountStatus = null;
    }

    public UpdateAccountDTO(String username,Integer accountId,  String email, Role role, AccountStatus accountStatus) {
        this.accountId = accountId;
        this.username = username;
        this.email = email;
        this.role = role;
        this.accountStatus = accountStatus;
    }
}
