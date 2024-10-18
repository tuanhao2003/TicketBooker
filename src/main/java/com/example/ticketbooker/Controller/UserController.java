package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Users.AddUserDTO;
import com.example.ticketbooker.DTO.Users.UpdateUserDTO;
import com.example.ticketbooker.Entity.Users;
import com.example.ticketbooker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String profile(Model model) {
        model.addAttribute("listUsers", userService.findAllUsers());
        model.addAttribute("createUserForm", new AddUserDTO());
        return "View/Admin/UserManagement/ListUsers";
    }

    @GetMapping("/details/{id}")
    public String profile(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("updateUserForm", new UpdateUserDTO());
        return "View/Admin/UserManagement/UserDetails";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("createUserForm") AddUserDTO addUserDTO, Model model) {
        try {
            boolean result = userService.addUser(addUserDTO);
            if (result) {
                model.addAttribute("successMessage", "Successfully created");
            } else {
                model.addAttribute("failedMessage", "User create has failed");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/users";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("updateUserForm") UpdateUserDTO updateUserDTO, Model model) {
        try {
            boolean result = userService.updateUser(updateUserDTO);
            if (result) {
                model.addAttribute("successMessage", "Successfully updated");
            } else {
                model.addAttribute("failedMessage", "User update has failed");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/users/details/"+updateUserDTO.getUserId();
    }

}
