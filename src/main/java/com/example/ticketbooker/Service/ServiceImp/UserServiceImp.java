package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Users.AddUserDTO;
import com.example.ticketbooker.DTO.Users.RequestIdUserDTO;
import com.example.ticketbooker.DTO.Users.ResponseUserDTO;
import com.example.ticketbooker.DTO.Users.UpdateUserDTO;
import com.example.ticketbooker.Entity.Users;
import com.example.ticketbooker.Repository.UserRepo;
import com.example.ticketbooker.Service.UserService;
import com.example.ticketbooker.Util.Enum.Gender;
import com.example.ticketbooker.Util.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepo usersRepo;

    @Override
    public boolean addUser(AddUserDTO dto) {
        try {
            Users user = UserMapper.fromAdd(dto);
            this.usersRepo.save(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateUser(UpdateUserDTO dto) {
        try {
            Users user = UserMapper.fromUpdate(dto.getUserId(), dto);
            this.usersRepo.save(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteUser(RequestIdUserDTO dto) {
        try {
            this.usersRepo.deleteById(dto.getUserId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public ResponseUserDTO findAllUsers() {
        ResponseUserDTO result = new ResponseUserDTO();
        ArrayList<Users> listUsers = new ArrayList<>();
        try {
            listUsers = this.usersRepo.findAll();
            result.setUsersCount(listUsers.size());
            result.setListUsers(listUsers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }

    @Override
    public Users findUserById(int userId) {
        Users user = null;
        try {
            user = this.usersRepo.findById(userId);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return user;
    }

    @Override
    public ResponseUserDTO findAllUserByName(String username) {
        ResponseUserDTO result = new ResponseUserDTO();
        ArrayList<Users> listUsers = new ArrayList<>();
        try {
            listUsers = this.usersRepo.findAllByFirstNameContainingOrLastNameContaining(username, username);
            result.setUsersCount(listUsers.size());
            result.setListUsers(listUsers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }

    @Override
    public ResponseUserDTO findAllUsersByGender(Gender gender) {
        ResponseUserDTO result = new ResponseUserDTO();
        ArrayList<Users> usersArrayList = new ArrayList<>();
        try {
            usersArrayList = this.usersRepo.findAllUsersByGender(gender);
            result.setUsersCount(usersArrayList.size());
            result.setListUsers(usersArrayList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }

    @Override
    public ArrayList<Users> sortUserByName(ArrayList<Users> users) {
        try {
            users.sort((user1, user2) -> {
                String name1 = user1.getFirstName() +" "+ user1.getLastName();
                String name2 = user2.getFirstName() +" "+ user2.getLastName();
                return name1.compareTo(name2);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return users;
    }

    @Override
    public ResponseUserDTO findAllUserByAddress(String address) {
        ResponseUserDTO result = new ResponseUserDTO();
        ArrayList<Users> usersArrayList = new ArrayList<>();
        try {
            usersArrayList = this.usersRepo.findAllUserByAddress(address);
            result.setUsersCount(usersArrayList.size());
            result.setListUsers(usersArrayList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }
}
