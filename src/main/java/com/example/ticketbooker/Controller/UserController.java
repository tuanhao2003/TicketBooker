package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Users.AddUserDTO;
import com.example.ticketbooker.DTO.Users.UpdateUserDTO;
import com.example.ticketbooker.Entity.Routes;
import com.example.ticketbooker.Service.RouteService;
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
        model.addAttribute("createUserForm", new AddUserDTO());
        return "View/Admin/UserManagement/ListUsers";
    }

    @GetMapping("/details/{id}")
    public String userDetails(@PathVariable int id, Model model) {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        if(userService.getUserById(id).getUsersCount() == 1){
            updateUserDTO = UserMapper.toUpdateDTO(userService.getUserById(id).getListUsers().get(0));
        }
        model.addAttribute("updateUserForm", updateUserDTO);
        return "View/Admin/UserManagement/UserDetails";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("createUserForm") AddUserDTO addUserDTO, Model model) {
        try {
            addUserDTO.setStatus(UserStatus.ACTIVE);
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
    public String updateUser(@ModelAttribute("updateUserForm") UpdateUserDTO updateUserDTO, Model model) {
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
