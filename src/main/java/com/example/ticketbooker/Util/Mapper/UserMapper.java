package com.example.ticketbooker.Util.Mapper;

import com.example.ticketbooker.DTO.Users.AddUserDTO;
import com.example.ticketbooker.DTO.Users.ResponseUserDTO;
import com.example.ticketbooker.DTO.Users.UpdateUserDTO;
import com.example.ticketbooker.Entity.Users;

import java.util.ArrayList;

public class UserMapper {
    public static Users fromAdd(AddUserDTO dto) {
        return Users.builder()
                .fullName(dto.getFullName())
                .phone(dto.getPhone())
                .userStatus(dto.getStatus())
                .build();
    }

    public static Users fromUpdate(UpdateUserDTO dto) {
        return Users.builder()
                .id(dto.getUserId())
                .fullName(dto.getFullName())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .dateOfBirth(dto.getDateOfBirth())
                .gender(dto.getGender())
                .profilePhoto(dto.getProfilePhoto())
                .userStatus(dto.getStatus())
                .build();
    }

    public static AddUserDTO toAddDTO(Users users) {
        return AddUserDTO.builder()
                .fullName(users.getFullName())
                .phone(users.getPhone())
                .status(users.getUserStatus())
                .build();
    }

    public static UpdateUserDTO toUpdateDTO(Users users) {
        return UpdateUserDTO.builder()
                .userId(users.getId())
                .accountId(users.getAccount() != null ? users.getAccount().getId() : null)
                .fullName(users.getFullName())
                .phone(users.getPhone())
                .address(users.getAddress())
                .dateOfBirth(users.getDateOfBirth())
                .gender(users.getGender())
                .profilePhoto(users.getProfilePhoto())
                .status(users.getUserStatus())
                .build();
    }

    public static ResponseUserDTO toResponseDTO(ArrayList<Users> users) {
        return ResponseUserDTO.builder()
                .usersCount(users.size())
                .listUsers(users)
                .build();
    }
}
