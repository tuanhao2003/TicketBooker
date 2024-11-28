package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Account.AccountDTO;
import com.example.ticketbooker.Entity.CustomUserDetails;
import com.example.ticketbooker.Service.AccountService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccessController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/admin/**")
    @PreAuthorize("hasRole('MANAGER')")
    public String managerDashboard() {
        return "redirect:/admin/users";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        System.out.println("Access denied");
        return "View/Util/404Page";
    }
    @GetMapping("/register")
    public String register() {
        System.out.println("register");
        return "View/Util/404Page";
    }
    @PostMapping("/register")
    public ResponseEntity<Object> addAccount(@RequestBody AccountDTO accountDTO, RedirectAttributes redirectAttributes) {
        System.out.println("Received data: " + accountDTO);
        try{
            if(accountService.createAccount(accountDTO) != null) {
                ;
                System.out.println("check inside create ACcount");
                return ResponseEntity.ok().build();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
            System.out.println("create account eror");
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/user-info")
    public String getUserInfo(HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            session.setAttribute("username", userDetails.getUsername());
            session.setAttribute("email", userDetails.getAccount().getEmail());
            session.setAttribute("role", userDetails.getAccount().getRole().toString());
            session.setAttribute("name", userDetails.getAccount().getRole().toString());
            session.setAttribute("role", userDetails.getAccount().getRole().toString());
            System.out.println( "Username: " + userDetails.getUsername() + " Email: " + userDetails.getAccount().getEmail() + " Role: " + userDetails.getAccount().getRole()) ;
            return "redirect:/auth/profile";
        }
        return "redirect:/auth";
    }
}
