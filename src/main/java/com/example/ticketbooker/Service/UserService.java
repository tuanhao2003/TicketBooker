package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Users.AddUserDTO;
import com.example.ticketbooker.DTO.Users.RequestIdUserDTO;
import com.example.ticketbooker.DTO.Users.ResponseUserDTO;
import com.example.ticketbooker.DTO.Users.UpdateUserDTO;
import com.example.ticketbooker.Entity.Users;
import com.example.ticketbooker.Util.Enum.Gender;

import java.util.ArrayList;

public interface UserService {
    public boolean addUser(AddUserDTO dto);
    public boolean updateUser(UpdateUserDTO dto);
    public boolean deleteUser(RequestIdUserDTO dto);
    public ResponseUserDTO findAllUsers();
    public Users findUserById(int userId);
    public ResponseUserDTO findAllUserByName(String username);
    public ResponseUserDTO findAllUsersByGender(Gender gender);
    public ArrayList<Users> sortUserByName(ArrayList<Users> users);
    public ResponseUserDTO findAllUserByAddress(String address);
}
