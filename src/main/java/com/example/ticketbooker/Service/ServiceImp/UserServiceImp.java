package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Users.AddUserDTO;
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
    public boolean updateUser(int id, UpdateUserDTO dto) {
        try {
            Users user = UserMapper.fromUpdate(id, dto);
            this.usersRepo.save(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteUser(int userId) {
        try {
            this.usersRepo.deleteById(userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    @Override
    public ArrayList<Users> findAllUsers() {
        ArrayList<Users> usersArrayList = new ArrayList<>();
        try {
            usersArrayList = this.usersRepo.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return usersArrayList;
    }

    @Override
    public Users findUserById(int userId) {
        Users user = null;
        try {
            user = this.usersRepo.findByUserId(userId);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return user;
    }

    @Override
    public ArrayList<Users> findAllUsersByGender(Gender gender) {
        ArrayList<Users> usersArrayList = new ArrayList<>();
        try {
            usersArrayList = this.usersRepo.findAllUsersByGender(gender);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return usersArrayList;
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
    public ArrayList<Users> findAllUserByAddress(String address) {
        ArrayList<Users> usersArrayList = new ArrayList<>();
        try {
            usersArrayList = this.usersRepo.findAllUserByAddress(address);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return usersArrayList;
    }
}
