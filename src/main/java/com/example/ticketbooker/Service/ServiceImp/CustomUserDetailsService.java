package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Account.AccountDTO;
import com.example.ticketbooker.Entity.Account;
import com.example.ticketbooker.Entity.CustomOAuth2User;
import com.example.ticketbooker.Entity.CustomUserDetails;
import com.example.ticketbooker.Repository.AccountRepo;
import com.example.ticketbooker.Service.AccountService;
import com.example.ticketbooker.Util.Mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepo accountRepo;

    //dang nhap thong thuong
    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        Optional<Account> account = accountRepo.findByEmail(username);
        if (account.isEmpty()) {
            account = accountRepo.findByUsername(username);
            if (account.isEmpty()) {
                System.out.println("account not found!!: username = " + username);
                throw new UsernameNotFoundException("Custom message: User not found with username: " + username);
            }
        }
        Account newAccount = account.get();
        System.out.println("new account is :" + newAccount);
        return new CustomUserDetails(newAccount);
    }


}



