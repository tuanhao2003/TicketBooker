package com.example.ticketbooker.Controller;

import com.example.ticketbooker.Entity.Users;
import com.example.ticketbooker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/profile")
    public String profile(Model model) {
        Users user = userService.findUserById(3);
        if (user == null) {
            throw new NullPointerException("User not found");
        }
        System.out.println(user.getLastName());
        model.addAttribute("user", user);

        return "View/User/Profile";
    }
}
