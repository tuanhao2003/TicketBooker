package com.example.ticketbooker.DTO.Users;

import com.example.ticketbooker.Util.Enum.UserStatus;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class AddUserDTO {
    private String fullName;
    private String phone;
    private UserStatus status;

    public AddUserDTO() {
        this.fullName = null;
        this.phone = null;
        this.status = UserStatus.ACTIVE;
    }
    public AddUserDTO(String fullName, String phone, UserStatus status) {
        this.fullName = fullName;
        this.phone = phone;
        this.status = status;
    }
}
