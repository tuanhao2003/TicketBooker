package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Users.AddUserRequest;
import com.example.ticketbooker.DTO.Users.UserIdRequest;
import com.example.ticketbooker.DTO.Users.UserResponse;
import com.example.ticketbooker.DTO.Users.UpdateUserRequest;
import com.example.ticketbooker.Util.Enum.Gender;

public interface UserService {
    boolean addUser(AddUserRequest dto);
    Integer addUserGetId(AddUserRequest dto);
    boolean updateUser(UpdateUserRequest dto);
    boolean deleteUser(UserIdRequest dto);
    UserResponse getAllUsers();
    UserResponse getUserById(int userId);
    UserResponse getAllUserByName(String username);
    UserResponse getAllUsersByGender(Gender gender);
    UserResponse getAllUserByAddress(String address);
    UserResponse sortUserByName(UserResponse users);
}