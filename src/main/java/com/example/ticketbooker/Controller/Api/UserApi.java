package com.example.ticketbooker.Controller.Api;

import com.example.ticketbooker.DTO.Routes.RouteDTO;
import com.example.ticketbooker.DTO.Routes.UpdateRouteDTO;
import com.example.ticketbooker.DTO.Users.UpdateUserRequest;
import com.example.ticketbooker.DTO.Users.UserDTO;
import com.example.ticketbooker.DTO.Users.UserIdRequest;
import com.example.ticketbooker.DTO.Users.UserResponse;
import com.example.ticketbooker.Service.UserService;
import com.example.ticketbooker.Util.Mapper.RouteMapper;
import com.example.ticketbooker.Util.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserApi {
    @Autowired
    private UserService userService;

    @DeleteMapping("/delete")
    public boolean deleteUser(@RequestBody UserIdRequest user) {
        boolean result = false;
        try {
            result = userService.deleteUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @PostMapping("/search")
    public UserResponse searchUser(@RequestBody String name) {
        UserResponse userResponse = new UserResponse();
        try {
            userResponse = this.userService.getAllUserByName(name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return userResponse;
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateAccountStatus(@PathVariable("id") Integer id, @RequestBody UserDTO userDTO) {
        UpdateUserRequest updateUserRequest = UserMapper.toUpdateDTO(userService.getUserById(id).getListUsers().get(0));
        if (updateUserRequest != null) {
            updateUserRequest.setStatus(userDTO.getStatus()); // Update only the status
            userService.updateUser(updateUserRequest);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
