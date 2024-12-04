package com.example.ticketbooker.Util;
import com.example.ticketbooker.Entity.Account;
import com.example.ticketbooker.Entity.CustomOAuth2User;
import com.example.ticketbooker.Entity.CustomUserDetails;
import com.example.ticketbooker.Service.ServiceImp.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated()
                && !authentication.getPrincipal().equals("anonymousUser");
    }

    public static Account extractAccount(Object principal) {
        if (principal instanceof CustomUserDetails customUserDetails) {
            return customUserDetails.getAccount();
        }
        if (principal instanceof CustomOAuth2User customOAuth2User) {
            return customOAuth2User.getAccount();
        }
        return null;
    }

    public static boolean updateAuthentication(CustomUserDetails customUserDetails) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            // Update the Authentication object in the SecurityContext
            authentication = new UsernamePasswordAuthenticationToken(
                    customUserDetails,
                    authentication.getCredentials(),
                    customUserDetails.getAuthorities()
            );
            //Set Authentication mới chứa thông tin đã cập nhật cho SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }

    }
}