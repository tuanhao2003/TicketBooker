package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Users.AddUserRequest;
import com.example.ticketbooker.DTO.Users.UpdateUserRequest;
import com.example.ticketbooker.Service.UserService;
import com.example.ticketbooker.Util.Enum.UserStatus;
import com.example.ticketbooker.Util.Mapper.UserMapper;
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
    public String allUsers(Model model) {
        model.addAttribute("responseDTO", userService.getAllUsers());
        model.addAttribute("createUserForm", new AddUserRequest());
        return "View/Admin/Users/ListUsers";
    }

    @GetMapping("/details/{id}")
    public String userDetails(@PathVariable int id, Model model) {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        if(userService.getUserById(id).getUsersCount() == 1){
            updateUserRequest = UserMapper.toUpdateDTO(userService.getUserById(id).getListUsers().get(0));
        }
        model.addAttribute("updateUserForm", updateUserRequest);
        return "View/Admin/Users/UserDetails";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("createUserForm") AddUserRequest addUserRequest, Model model) {
        try {
            addUserRequest.setStatus(UserStatus.ACTIVE);
            boolean result = userService.addUser(addUserRequest);
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
    public String updateUser(@ModelAttribute("updateUserForm") UpdateUserRequest updateUserRequest, Model model) {
        try {
            boolean result = userService.updateUser(updateUserRequest);
            if (result) {
                model.addAttribute("successMessage", "Successfully updated");
            } else {
                model.addAttribute("failedMessage", "User update has failed");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/users/details/"+ updateUserRequest.getUserId();
    }

}
