package com.example.ticketbooker.DTO.Users;

import com.example.ticketbooker.Util.Enum.Gender;
import com.example.ticketbooker.Util.Enum.UserStatus;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class UpdateUserRequest {
    private Integer userId;
    private String fullName;
    private String phone;
    private String address;
    private Date dateOfBirth;
    private Gender gender;
    private byte[] profilePhoto;
    private UserStatus status;

    public UpdateUserRequest() {
        this.userId = null;
        this.fullName = "";
        this.phone = "";
        this.address = null;
        this.dateOfBirth = null;
        this.gender = null;
        this.profilePhoto = null;
        this.status = UserStatus.ACTIVE;
    }

    public UpdateUserRequest(Integer userId, String fullName, String phone, String address, Date dateOfBirth, Gender gender, byte[] profilePhoto, UserStatus status) {
        this.userId = userId;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.profilePhoto = profilePhoto;
        this.status = status;
    }
}
