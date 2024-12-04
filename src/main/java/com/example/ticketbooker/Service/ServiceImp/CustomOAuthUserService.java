package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Account.AccountDTO;
import com.example.ticketbooker.Entity.Account;
import com.example.ticketbooker.Entity.CustomOAuth2User;
import com.example.ticketbooker.Entity.Users;
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
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
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
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String name = (String) attributes.get("name");
        String email = (String) attributes.get("email");

        //Trong trường hợp không có tên (do người dùng chưa nhập) thì lấy tên dđăng nhập làm tên
        if (name == null) {
            name = (String) attributes.get("login");  // GitHub username if name is not available
        }

        //Xu lí ấy email khi đăng nhập github
        String userInfoEndpointUri = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUri();
        String emailEndpoint = "https://api.github.com/user/emails";
        if (userInfoEndpointUri.contains("github")) {
            RestTemplate restTemplate = new RestTemplate();
            String accessToken = userRequest.getAccessToken().getTokenValue();

            var headers = new org.springframework.http.HttpHeaders();
            headers.setBearerAuth(accessToken);

            var entity = new org.springframework.http.HttpEntity<>(headers);

            List<Map<String, Object>> emails = restTemplate.exchange(
                    emailEndpoint,
                    org.springframework.http.HttpMethod.GET,
                    entity,
                    List.class
            ).getBody();

            if (emails != null && !emails.isEmpty()) {
                email = emails.stream()
                        .filter(emailGithub -> Boolean.TRUE.equals(emailGithub.get("primary")))
                        .map(emailGithub -> emailGithub.get("email").toString())
                        .findFirst()
                        .orElse(null);
            }
        }

        System.out.println("Ten vaf email: " + name + "  " + email);
        System.out.println("Oauth tu authenticate: "+oAuth2User);

        // Kiểm tra hoặc tạo tài khoản trong cơ sở dữ liệu
        Optional<Account> account = accountRepo.findByEmail(email);
        if (account.isPresent()) {
            Account resultAccount = account.get();
            System.out.println("account ton tai is :" + resultAccount);
            return new CustomOAuth2User(resultAccount, oAuth2User.getAttributes()) {
            };

        } else {
            Account newAccount = new Account();
            newAccount.setEmail(email);
            newAccount.setUsername(email);
            newAccount.setRole(Role.CUSTOMER);
            newAccount.setAccountStatus(AccountStatus.ACTIVE);
            newAccount.setUser(Users.builder().fullName(name).build());
            System.out.println("OAuth2User custom tu tao:" + newAccount);
            return new CustomOAuth2User(newAccount, oAuth2User.getAttributes());
        }


    }
}
