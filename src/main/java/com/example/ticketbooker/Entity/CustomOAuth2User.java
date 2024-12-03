package com.example.ticketbooker.Entity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class CustomOAuth2User extends DefaultOAuth2User {

    private final Account account; // Dữ liệu người dùng từ cơ sở dữ liệu

    public CustomOAuth2User(Account account, Map<String, Object> attributes) {
        super(Collections.singleton(
                new SimpleGrantedAuthority("ROLE_" + account.getRole().name())), attributes, "email"); // Sử dụng "email" làm key chính
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public String getName() {
        // Trả về tên người dùng hoặc giá trị từ OAuth2
        return account.getUser().getFullName();
    }
}