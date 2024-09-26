package com.example.ticketbooker.Service;

import com.example.ticketbooker.DTO.Users.AddUserDTO;
import com.example.ticketbooker.DTO.Users.UpdateUserDTO;
import com.example.ticketbooker.Entity.Users;
import com.example.ticketbooker.Util.Enum.Gender;

import java.util.ArrayList;

public interface UserService {
    public boolean addUser(AddUserDTO dto);
    public boolean updateUser(int id, UpdateUserDTO dto);
    public boolean deleteUser(int userId);
    public ArrayList<Users> findAllUsers();
    public Users findUserById(int userId);
    public ArrayList<Users> findAllUsersByGender(Gender gender);
    public ArrayList<Users> sortUserByName(ArrayList<Users> users);
    public ArrayList<Users> findAllUserByAddress(String address);
}
