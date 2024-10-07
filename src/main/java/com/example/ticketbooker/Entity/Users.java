package com.example.ticketbooker.Entity;

import com.example.ticketbooker.Util.Enum.Gender;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "accountId", nullable = true)
    private Integer accountId;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "dateOfBirth", nullable = true)
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = true)
    private Gender gender;

    @Column(name = "profilePhoto", nullable = true)
    private String profilePhoto;

    public Users() {
        this.userId = 0;
        this.accountId = 0;
        this.firstName = "";
        this.lastName = "";
        this.address = "";
        this.dateOfBirth = null;
        this.gender = null;
        this.profilePhoto = "";
    }

    public Users(Integer usersId, Integer accountId,String firstName, String lastName, String address, Date dateOfBirth, Gender gender, String profilePhoto) {
        this.userId = usersId;
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.profilePhoto = profilePhoto;
    }
}
