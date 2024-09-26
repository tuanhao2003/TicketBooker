package com.example.ticketbooker.DTO.Users;

import com.example.ticketbooker.Util.Enum.Gender;
import lombok.Data;

import java.sql.Date;

@Data
public class AddUserDTO {
    private int accountId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Gender gender;

    public AddUserDTO() {
        this.accountId = 0;
        this.firstName = "";
        this.lastName = "";
        this.dateOfBirth = null;
        this.gender = null;
    }
    public AddUserDTO(int accountId,String firstName, String lastName, Gender gender, Date dateOfBirth,String profilePhoto) {
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }
}
