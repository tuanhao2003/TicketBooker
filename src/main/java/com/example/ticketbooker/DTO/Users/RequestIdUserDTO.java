package com.example.ticketbooker.DTO.Users;

import lombok.Data;

@Data
public class RequestIdUserDTO {
    Integer userId;
    public RequestIdUserDTO(Integer userId) {
        this.userId = userId;
    }
    public RequestIdUserDTO() {
        this.userId = null;
    }
}
