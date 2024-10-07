package com.example.ticketbooker.Controller.Api;

import com.example.ticketbooker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/userManagement")
public class UserApi {
    @Autowired
    private UserService userService;

    @DeleteMapping
    public boolean deleteUser(@RequestBody int userId) {
        boolean result = false;
        try {
            result = userService.deleteUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
