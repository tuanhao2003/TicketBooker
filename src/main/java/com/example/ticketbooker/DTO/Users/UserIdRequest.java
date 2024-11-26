package com.example.ticketbooker.DTO.Users;

import lombok.Data;

@Data
public class UserIdRequest {
    Integer userId;
    public UserIdRequest(Integer userId) {
        this.userId = userId;
    }
    public UserIdRequest() {
        this.userId = null;
    }
}
