package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Users.AddUserRequest;
import com.example.ticketbooker.DTO.Users.UpdateUserRequest;
import com.example.ticketbooker.DTO.Users.UserDTO;
import com.example.ticketbooker.Service.UserService;
import com.example.ticketbooker.Util.Enum.UserStatus;
import com.example.ticketbooker.Util.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String allUsers(Model model,
                           @RequestParam(value = "page", defaultValue = "0") int page,
                           @RequestParam(value = "size",defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<UserDTO> userDTOPage = this.userService.getAllUsers(pageable);
        model.addAttribute("createUserForm", new AddUserRequest());
        model.addAttribute("users", userDTOPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userDTOPage.getTotalPages());
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
            System.out.println("add user object: "+addUserRequest);
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
