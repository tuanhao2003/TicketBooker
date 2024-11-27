package com.example.ticketbooker.Config.Security;

import com.example.ticketbooker.Entity.Account;
import com.example.ticketbooker.Entity.CustomUserDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Lấy thông tin người dùng từ Authentication
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails userDetails) {
            // Lưu thông tin vào HttpSession
            request.getSession().setAttribute("accountId", userDetails.getAccount().getId());
            request.getSession().setAttribute("username", userDetails.getUsername());
            request.getSession().setAttribute("role", userDetails.getAuthorities());
        }
        System.out.println("Account Login Detail: " + principal.toString());
        // Chuyển hướng đến trang đích sau khi đăng nhập thành công
        response.sendRedirect("/auth/profile");
    }
}
