package com.example.ticketbooker.DTO.Users;

import com.example.ticketbooker.Entity.Users;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class UserResponse {
    private int usersCount;
    private ArrayList<Users> listUsers;

    public UserResponse(Integer usersCount, ArrayList<Users> listUsers) {
        this.usersCount = usersCount;
        this.listUsers = listUsers;
    }
    public UserResponse() {
        this.usersCount = 0;
        this.listUsers = new ArrayList<>();
    }
}
