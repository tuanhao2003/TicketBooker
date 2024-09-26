package com.example.ticketbooker.DTO.Users;

import com.example.ticketbooker.Util.Enum.Gender;
import lombok.Data;

import java.sql.Date;

@Data
public class UpdateUserDTO {
    private String firstName;
    private String lastName;
    private String address;
    private Date dateOfBirth;
    private Gender gender;
    private String profilePhoto;

    public UpdateUserDTO() {
        this.firstName = "";
        this.lastName = "";
        this.address = "";
        this.dateOfBirth = null;
        this.gender = null;
        this.profilePhoto = "";
    }
    public UpdateUserDTO(String firstName, String lastName, String address, Date dateOfBirth, Gender gender, String profilePhoto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.profilePhoto = profilePhoto;
    }
}
