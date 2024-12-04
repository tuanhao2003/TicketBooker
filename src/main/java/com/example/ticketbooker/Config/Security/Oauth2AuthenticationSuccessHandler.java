package com.example.ticketbooker.Config.Security;

import com.example.ticketbooker.Entity.CustomUserDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Lấy thông tin người dùng từ Authentication
        Object principal = authentication.getPrincipal();
        // Lưu thông tin vào HttpSession
        if (principal instanceof CustomUserDetails userDetails) {
            //Đăng nhâp bằng account hệ thống
            System.out.println("Class name Login UserDetail: " +userDetails.getClass().getName());
            request.getSession().setAttribute("accountId", userDetails.getAccount().getId());
            request.getSession().setAttribute("username", userDetails.getUsername());
            request.getSession().setAttribute("role", userDetails.getAuthorities().toString());
            request.getSession().setAttribute("email", userDetails.getAccount().getEmail());
            // Chuyển hướng đến trang đích sau khi đăng nhập thành công
            response.sendRedirect("/user-info");
        } else if (principal instanceof OAuth2User oauth2User) {
            System.out.println("if principal instance of oauth2: " + oauth2User.getClass().getName());
            request.getSession().setAttribute("username", oauth2User.getAttribute("name"));
            request.getSession().setAttribute("email", oauth2User.getAttribute("email"));
            request.getSession().setAttribute("role", "Customer");
            response.sendRedirect("/user-info");
        }
    }
}
