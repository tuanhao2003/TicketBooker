package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Users.*;
import com.example.ticketbooker.Util.Enum.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    boolean addUser(AddUserRequest dto);
    Integer addUserGetId(AddUserRequest dto);
    boolean updateUser(UpdateUserRequest dto);
    boolean deleteUser(UserIdRequest dto);
    UserResponse getAllUsers();
    Page<UserDTO> getAllUsers(Pageable pageable);
    UserResponse getUserById(int userId);
    UserResponse getAllUserByName(String username);
    UserResponse getAllUsersByGender(Gender gender);
    UserResponse getAllUserByAddress(String address);
    UserResponse sortUserByName(UserResponse users);
}