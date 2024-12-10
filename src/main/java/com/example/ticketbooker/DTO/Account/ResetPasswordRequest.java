package com.example.ticketbooker.DTO.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResetPasswordRequest {
    private String username;
    private String newPassword;
}
