package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Account.AccountDTO;
import com.example.ticketbooker.Entity.Account;
import com.example.ticketbooker.Entity.CustomOAuth2User;
import com.example.ticketbooker.Repository.AccountRepo;
import com.example.ticketbooker.Util.Enum.AccountStatus;
import com.example.ticketbooker.Util.Enum.Role;
import com.example.ticketbooker.Util.Mapper.AccountMapper;
import com.example.ticketbooker.Util.RedirectToPasswordCreationException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CustomOAuthUserService extends DefaultOAuth2UserService {
    private final HttpSession httpSession;
    private final AccountRepo accountRepo;

    @Autowired
    public CustomOAuthUserService(AccountRepo accountRepo,HttpSession httpSession) {
        this.accountRepo = accountRepo;
        this.httpSession = httpSession;
    }
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {

        // Xử lý đăng nhập qua OAuth2
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        // Kiểm tra hoặc tạo tài khoản trong cơ sở dữ liệu
        Optional<Account> account = accountRepo.findByEmail(email);
        if (account.isPresent()) {
            Account newAccount = account.get();
            System.out.println("account ton tai is :" + newAccount);
            return new CustomOAuth2User(newAccount, oAuth2User.getAttributes()) {
            };

        } else {
            httpSession.setAttribute("oauth2_email", email);
            httpSession.setAttribute("oauth2_name", name);
            Account newAccount = new Account();
            newAccount.setEmail(email);
            newAccount.setUsername(name);
            newAccount.setRole(Role.CUSTOMER);
            newAccount.setAccountStatus(AccountStatus.ACTIVE);
            System.out.println("OAuth2User custom tu tao:" + newAccount);
            return new CustomOAuth2User(newAccount, oAuth2User.getAttributes());

        }


    }
}
