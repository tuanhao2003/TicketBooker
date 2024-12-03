package com.example.ticketbooker.Entity;

import com.example.ticketbooker.Util.Enum.AccountStatus;
import com.example.ticketbooker.Util.Enum.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountId", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users user;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "email",nullable = false, length = 100)
    private String email;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "accountStatus", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    public Account() {
        this.id = null;
        this.user = null;
        this.username = "";
        this.password = "";
        this.email = null;
        this.role = Role.CUSTOMER;
        this.accountStatus = AccountStatus.ACTIVE;
    }

    public Account(Integer id, Users user, String username, String password, String email, Role role, AccountStatus accountStatus) {
        this.id = id;
        this.user = user;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.accountStatus = accountStatus;
    }
}