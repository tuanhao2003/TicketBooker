package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Users.AddUserRequest;
import com.example.ticketbooker.DTO.Users.UserIdRequest;
import com.example.ticketbooker.DTO.Users.UserResponse;
import com.example.ticketbooker.DTO.Users.UpdateUserRequest;
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
    public boolean addUser(AddUserRequest dto) {
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
    public Integer addUserGetId(AddUserRequest dto) {
        Users user;
        try {
            user = UserMapper.fromAdd(dto);
            user = this.usersRepo.save(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return user.getId();
    }

    @Override
    public boolean updateUser(UpdateUserRequest dto) {
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
    public boolean deleteUser(UserIdRequest dto) {
        try {
            this.usersRepo.deleteById(dto.getUserId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public UserResponse getAllUsers() {
        UserResponse result = new UserResponse();
        try {
            result = UserMapper.toResponseDTO(this.usersRepo.findAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }

    @Override
    public UserResponse getUserById(int userId) {
        UserResponse result = new UserResponse();
        try {
            result = UserMapper.toResponseDTO(this.usersRepo.findAllById(userId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }

    @Override
    public UserResponse getAllUserByName(String username) {
        UserResponse result = new UserResponse();
        try {
            result = UserMapper.toResponseDTO(this.usersRepo.findByFullNameLike(username));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }

    @Override
    public UserResponse getAllUsersByGender(Gender gender) {
        UserResponse result = new UserResponse();
        try {
            result = UserMapper.toResponseDTO(this.usersRepo.findAllByGender(gender));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }

    @Override
    public UserResponse sortUserByName(UserResponse users) {
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
    public UserResponse getAllUserByAddress(String address) {
        UserResponse result = new UserResponse();
        try {
            result = UserMapper.toResponseDTO(this.usersRepo.findAllByAddress(address));
        } catch (Exception e){
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }
}
