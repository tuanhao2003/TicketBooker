package com.example.ticketbooker.Entity;

import com.example.ticketbooker.Util.Enum.Gender;
import com.example.ticketbooker.Util.Enum.UserStatus;
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
    @Column(name = "userId", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;

    @Column(name = "fullName", nullable = false)
    private String fullName;

    @Column(name = "phone", nullable = false, length = 11)
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Lob
    @Column(name = "profilePhoto")
    private byte[] profilePhoto;


    @Column(name = "userStatus", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    public Users() {
        this.id = null;
        this.account = null;
        this.fullName = "";
        this.phone = "";
        this.address = null;
        this.dateOfBirth = null;
        this.gender = null;
        this.profilePhoto = null;
        this.userStatus = UserStatus.ACTIVE;
    }

    public Users(Integer id, Account account, String fullName, String phone, String address, Date dateOfBirth, Gender gender, byte[] profilePhoto, UserStatus userStatus) {
        this.id = id;
        this.account = account;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.profilePhoto = profilePhoto;
        this.userStatus = userStatus;
    }
}