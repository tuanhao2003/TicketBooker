package com.example.ticketbooker.Config.Security;

import com.example.ticketbooker.Entity.Account;
import com.example.ticketbooker.Entity.CustomOAuth2User;
import com.example.ticketbooker.Entity.CustomUserDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Lấy thông tin người dùng từ Authentication
        Object principal = authentication.getPrincipal();

        if (principal instanceof CustomUserDetails userDetails) {
            //Đăng nhâp bằng account hệ thống
            Account account = userDetails.getAccount();
            request.getSession().setAttribute("accountId", userDetails.getAccount().getId());
            request.getSession().setAttribute("username", userDetails.getUsername());
            request.getSession().setAttribute("role", userDetails.getAuthorities().toString());
            request.getSession().setAttribute("email", userDetails.getAccount().getEmail());
            // Chuyển hướng đến trang đích sau khi đăng nhập thành công
            if(account.getRole().toString().equals("MANAGER")){
                response.sendRedirect("/admin/statistics");
            }else {
                response.sendRedirect("/fuba");
            }

        } else if (principal instanceof CustomOAuth2User customOAuth2User) {
            //Đăng nhập bằng oauth, chưa có tk trong hệ thống
            System.out.println("Oauth2 user: " + customOAuth2User);
            if(customOAuth2User.getAccount().getPassword().isEmpty()) {
                response.sendRedirect("/new-password");
            }else
                response.sendRedirect("/fuba");
        }
    }
}
