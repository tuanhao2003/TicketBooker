package com.example.ticketbooker.DTO.Users;

import com.example.ticketbooker.Util.Enum.Gender;
import com.example.ticketbooker.Util.Enum.UserStatus;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class UpdateUserDTO {
    private Integer userId;
    private Integer accountId;
    private String fullName;
    private String phone;
    private String address;
    private Date dateOfBirth;
    private Gender gender;
    private String profilePhoto;
    private UserStatus status;

    public UpdateUserDTO() {
        this.userId = null;
        this.accountId = null;
        this.fullName = "";
        this.phone = "";
        this.address = null;
        this.dateOfBirth = null;
        this.gender = null;
        this.profilePhoto = null;
        this.status = UserStatus.ACTIVE;
    }

    public UpdateUserDTO(Integer userId, Integer accountId, String fullName, String phone, String address, Date dateOfBirth, Gender gender, String profilePhoto, UserStatus status) {
        this.userId = userId;
        this.accountId = accountId;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.profilePhoto = profilePhoto;
        this.status = status;
    }
}
