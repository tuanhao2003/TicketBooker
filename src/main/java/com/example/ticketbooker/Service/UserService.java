package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Users.AddUserDTO;
import com.example.ticketbooker.DTO.Users.RequestIdUserDTO;
import com.example.ticketbooker.DTO.Users.ResponseUserDTO;
import com.example.ticketbooker.DTO.Users.UpdateUserDTO;
import com.example.ticketbooker.Util.Enum.Gender;

public interface UserService {
    public boolean addUser(AddUserDTO dto);
    public boolean updateUser(UpdateUserDTO dto);
    public boolean deleteUser(RequestIdUserDTO dto);
    public ResponseUserDTO getAllUsers();
    public ResponseUserDTO getUserById(int userId);
    public ResponseUserDTO getAllUserByName(String username);
    public ResponseUserDTO getAllUsersByGender(Gender gender);
    public ResponseUserDTO getAllUserByAddress(String address);
    public ResponseUserDTO sortUserByName(ResponseUserDTO users);
}