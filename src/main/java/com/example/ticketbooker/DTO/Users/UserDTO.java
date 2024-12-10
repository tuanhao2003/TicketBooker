package com.example.ticketbooker.DTO.Users;

import com.example.ticketbooker.Util.Enum.Gender;
import com.example.ticketbooker.Util.Enum.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer userId;
    private String fullName;
    private String phone;
    private String address;
    private Date dateOfBirth;
    private Gender gender;
    private byte[] profilePhoto;
    private UserStatus status;
}
