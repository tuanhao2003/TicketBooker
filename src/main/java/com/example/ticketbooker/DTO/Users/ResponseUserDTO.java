package com.example.ticketbooker.DTO.Users;

import com.example.ticketbooker.Entity.Users;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.sql.Date;
import java.util.ArrayList;

@Data
@Builder
public class ResponseUserDTO {
    private int usersCount;
    private ArrayList<Users> listUsers;

    public ResponseUserDTO(Integer usersCount, ArrayList<Users> listUsers) {
        this.usersCount = usersCount;
        this.listUsers = listUsers;
    }
    public ResponseUserDTO() {
        this.usersCount = 0;
        this.listUsers = new ArrayList<>();
    }
}
