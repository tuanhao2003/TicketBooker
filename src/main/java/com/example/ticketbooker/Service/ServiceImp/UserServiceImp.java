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
            Users user = UserMapper.fromUpdate(dto);
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
    public ResponseUserDTO getAllUsers() {
        ResponseUserDTO result = new ResponseUserDTO();
        try {
            result = UserMapper.toResponseDTO(this.usersRepo.findAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }

    @Override
    public ResponseUserDTO getUserById(int userId) {
        ResponseUserDTO result = new ResponseUserDTO();
        try {
            result = UserMapper.toResponseDTO(this.usersRepo.findAllById(userId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }

    @Override
    public ResponseUserDTO getAllUserByName(String username) {
        ResponseUserDTO result = new ResponseUserDTO();
        try {
            result = UserMapper.toResponseDTO(this.usersRepo.findByFullNameContainingIgnoreCase(username));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }

    @Override
    public ResponseUserDTO getAllUsersByGender(Gender gender) {
        ResponseUserDTO result = new ResponseUserDTO();
        try {
            result = UserMapper.toResponseDTO(this.usersRepo.findAllByGender(gender));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }

    @Override
    public ResponseUserDTO sortUserByName(ResponseUserDTO users) {
        try {
            if (!users.getListUsers().isEmpty()) {
                users.getListUsers().sort((user1, user2) -> user1.getFullName().compareTo(user2.getFullName()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return users;
        }
        return users;
    }

    @Override
    public ResponseUserDTO getAllUserByAddress(String address) {
        ResponseUserDTO result = new ResponseUserDTO();
        try {
            result = UserMapper.toResponseDTO(this.usersRepo.findAllByAddress(address));
        } catch (Exception e){
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }
}
