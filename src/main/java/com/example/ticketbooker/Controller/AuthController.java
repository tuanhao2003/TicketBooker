package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Account.AccountDTO;
import com.example.ticketbooker.Entity.Account;
import com.example.ticketbooker.Entity.CustomOAuth2User;
import com.example.ticketbooker.Entity.CustomUserDetails;
import com.example.ticketbooker.Service.AccountService;
import com.example.ticketbooker.Service.ServiceImp.AccountServiceImp;
import com.example.ticketbooker.Util.SecurityUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping()
    public String login(Authentication authentication) {
        // Nếu người dùng đã đăng nhập, điều hướng đến /home
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/fuba";
        }
        return "View/Util/Login"; // Hiển thị trang login nếu chưa đăng nhập
    }

    @GetMapping("/profile")
    public String getUserProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return "View/Util/Login";
        }
        Object principal = authentication.getPrincipal();
        String username = "";// Giả sử bạn đã lưu tên người dùng vào session
        String email = "";
        String role = "";
        Account account = SecurityUtils.extractAccount(principal);
        if (account != null) {
            username = account.getUsername();
            email = account.getEmail();
            role = account.getRole().toString();
        }
        model.addAttribute("name", username);
        model.addAttribute("email", email);
        model.addAttribute("role", role);
        // Trả về thông tin người dùng hoặc giao diện
        return "View/User/Registered/Profile"; // Trả về view tên là "profile"
    }

    @GetMapping(params = "error")
    public String loginError(@RequestParam(value = "error", required = false) Boolean error, Model model) {
        System.out.println("somthing" + error);
        model.addAttribute("error", "Sai tên đăng nhập hoặc mật khẩu! ");
        return "View/Util/Login";
    }

}
