package com.example.ticketbooker.DTO.Users;

import com.example.ticketbooker.Util.Enum.Gender;
import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Getter
@Builder
public class ResponseUserDTO {
    private Integer accountId;
    private String firstName;
    private String lastName;
    private String address;
    private Date dateOfBirth;
    private Gender gender;
    private String profilePhoto;

    public ResponseUserDTO() {
        this.accountId = null;
        this.firstName = "";
        this.lastName = "";
        this.address = "";
        this.dateOfBirth = null;
        this.gender = null;
        this.profilePhoto = "";
    }
    public ResponseUserDTO(Integer accountId,String firstName, String lastName, String address, Date dateOfBirth, Gender gender, String profilePhoto) {
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.profilePhoto = profilePhoto;
    }
}
