package com.example.ticketbooker.Util.Mapper;

import com.example.ticketbooker.DTO.Users.AddUserRequest;
import com.example.ticketbooker.DTO.Users.UserResponse;
import com.example.ticketbooker.DTO.Users.UpdateUserRequest;
import com.example.ticketbooker.Entity.Users;

import java.util.ArrayList;

public class UserMapper {
    public static Users fromAdd(AddUserRequest dto) {
        return Users.builder()
                .fullName(dto.getFullName())
                .phone(dto.getPhone())
                .userStatus(dto.getStatus())
                .build();
    }

    public static Users fromUpdate(UpdateUserRequest dto) {
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

    public static UpdateUserRequest toUpdateDTO(Users entity) {
        return UpdateUserRequest.builder()
                .userId(entity.getId())
                .accountId(entity.getAccount() != null ? entity.getAccount().getId() : null)
                .fullName(entity.getFullName())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .dateOfBirth(entity.getDateOfBirth())
                .gender(entity.getGender())
                .profilePhoto(entity.getProfilePhoto())
                .status(entity.getUserStatus())
                .build();
    }

    public static UserResponse toResponseDTO(ArrayList<Users> listEntities) {
        return UserResponse.builder()
                .usersCount(listEntities.size())
                .listUsers(listEntities)
                .build();
    }
}
