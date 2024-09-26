package com.example.ticketbooker.Util.Mapper;

import com.example.ticketbooker.DTO.Users.AddUserDTO;
import com.example.ticketbooker.DTO.Users.ResponseUserDTO;
import com.example.ticketbooker.DTO.Users.UpdateUserDTO;
import com.example.ticketbooker.Entity.Users;

import java.sql.Date;

public class UserMapper {
    public static Users fromAdd(AddUserDTO dto) {
        return Users.builder().accountId(dto.getAccountId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .dateOfBirth(dto.getDateOfBirth())
                .gender(dto.getGender())
                .build();
    }

    public static Users fromUpdate(int id, UpdateUserDTO dto) {
        return Users.builder()
                .userId(id)
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .address(dto.getAddress())
                .dateOfBirth(dto.getDateOfBirth())
                .gender(dto.getGender())
                .profilePhoto(dto.getProfilePhoto())
                .build();
    }

    public static ResponseUserDTO fromUser(Users user) {
        return ResponseUserDTO.builder().accountId(user.getAccountId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .address(user.getAddress())
                .dateOfBirth(user.getDateOfBirth())
                .profilePhoto(user.getProfilePhoto())
                .build();
    }
}
