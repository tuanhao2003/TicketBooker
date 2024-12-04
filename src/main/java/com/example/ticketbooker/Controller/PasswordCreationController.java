package com.example.ticketbooker.Controller;
import com.example.ticketbooker.DTO.Account.AccountDTO;
import com.example.ticketbooker.DTO.Users.AddUserRequest;
import com.example.ticketbooker.DTO.Users.UpdateUserRequest;
import com.example.ticketbooker.Entity.Account;
import com.example.ticketbooker.Entity.CustomOAuth2User;
import com.example.ticketbooker.Entity.CustomUserDetails;
import com.example.ticketbooker.Entity.Users;
import com.example.ticketbooker.Repository.AccountRepo;
import com.example.ticketbooker.Service.AccountService;
import com.example.ticketbooker.Service.UserService;
import com.example.ticketbooker.Util.Enum.AccountStatus;
import com.example.ticketbooker.Util.Enum.Role;
import com.example.ticketbooker.Util.Enum.UserStatus;
import com.example.ticketbooker.Util.SecurityUtils;
import jakarta.servlet.http.HttpSession;
import javassist.expr.Instanceof;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;


@Controller
public class PasswordCreationController {

    private final AccountService accountService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession httpSession;

    @Autowired
    public PasswordCreationController(UserService userService,AccountService accountService,PasswordEncoder passwordEncoder, HttpSession httpSession) {
        this.userService = userService;
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
        this.httpSession = httpSession;
    }

    @GetMapping("/new-password")
    public String showPasswordCreationPage(Model model) {
        // Lấy data người dùng đã đặng nhâập
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isLoggedIn = SecurityUtils.isLoggedIn();
        if (isLoggedIn) {
            Account account = SecurityUtils.extractAccount(authentication.getPrincipal());
            model.addAttribute("email", account.getEmail());
            model.addAttribute("username", account.getUsername());
            return "View/Util/NewPassword";

        }
        return "redirect:/fuba";
    }

    @PostMapping("/new-password")
    public String handlePasswordCreation(@RequestParam String password) {
        // Lấy email từ thông tin đăng nhập
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isLoggedIn = SecurityUtils.isLoggedIn();
        if (isLoggedIn) {
            Object principal = authentication.getPrincipal();
            if(principal instanceof CustomUserDetails) {
                return "redirect:/fuba";
            }
            else if(principal instanceof CustomOAuth2User customOAuth2User) {
                System.out.println("OAuthUSer: "+customOAuth2User);
                Account account = SecurityUtils.extractAccount(principal);
                String email = account.getEmail();
                String fullname = account.getUser().getFullName();
                //Khởi tạo tài khoản lưu vào dtb
                AccountDTO accountDTO = new AccountDTO();
                accountDTO.setEmail(email);
                accountDTO.setUsername(email);
                accountDTO.setPassword(password);
                accountDTO.setRole(Role.valueOf("CUSTOMER"));
                accountDTO.setAccountStatus(AccountStatus.ACTIVE);
                accountDTO.setUser(Users.builder().fullName(fullname).build());
                if(accountService.createAccountWithUser(accountDTO) != null) {
                    return "redirect:/auth/profile";
                }
                return "redirect:/new-password?error";
                }
        }
        return "redirect:/fuba";
    }
}