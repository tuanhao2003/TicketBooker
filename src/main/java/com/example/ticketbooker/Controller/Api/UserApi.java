package com.example.ticketbooker.Controller.Api;

import com.example.ticketbooker.DTO.Users.RequestIdUserDTO;
import com.example.ticketbooker.DTO.Users.ResponseUserDTO;
import com.example.ticketbooker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserApi {
    @Autowired
    private UserService userService;

    @DeleteMapping("/delete")
    public boolean deleteUser(@RequestBody RequestIdUserDTO user) {
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
    public ResponseUserDTO searchUser(@RequestBody String name) {
        ResponseUserDTO responseUserDTO = new ResponseUserDTO();
        try {
            responseUserDTO = this.userService.getAllUserByName(name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return responseUserDTO;
    }
}
