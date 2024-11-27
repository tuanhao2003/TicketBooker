package com.example.ticketbooker.Config.Security;

import com.example.ticketbooker.Entity.Account;
import com.example.ticketbooker.Repository.AccountRepo;
import com.example.ticketbooker.Service.ServiceImp.AccountServiceImp;
import com.example.ticketbooker.Service.ServiceImp.CustomOAuthUserService;
import com.example.ticketbooker.Service.ServiceImp.CustomUserDetailsService;
import com.example.ticketbooker.Util.RedirectToPasswordCreationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomOAuthUserService customOAuthUserService;

    public SecurityConfig(CustomOAuthUserService customOAuthUserService) {
        this.customOAuthUserService = customOAuthUserService;
    }


    @Bean
    public CustomUserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();  // Define the success handler here
    }



    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService())
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/fuba","/register","/access-denied","/new-password","/404").permitAll() // Các URL yêu cầu đăng nhập
                        .requestMatchers("/fuba/**").permitAll() // Các URL yêu cầu đăng nhập
                        .requestMatchers("/favicon.icon").permitAll()
                        .requestMatchers("/auth").anonymous()
                                .requestMatchers("/admin/**").hasRole("MANAGER")
//                                .requestMatchers("").hasRole("CUSTOMER")
//                                .requestMatchers("").hasRole("STAFF")
                        .anyRequest().authenticated() // Các URL còn lại cần xác thực
//                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/auth")
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuthUserService) // Sử dụng CustomOAuth2UserService
                        )
                        .successHandler(customAuthenticationSuccessHandler())
                )
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin((form) -> form
                        .loginPage("/auth")
                        .successHandler(customAuthenticationSuccessHandler())
                        .permitAll()
                )
                .rememberMe((remember) -> remember
                        .key("uniqueAndSecret") // Mã khóa để bảo vệ cookie
                        .tokenValiditySeconds(1209600) // Thời gian tồn tại cookie (mặc định là 2 tuần)
                ) // Kích hoạt tính năng remember-me
                .logout((logout) -> logout
                        .logoutUrl("/logout") // Đường dẫn để đăng xuất
                        .logoutSuccessUrl("/login") // Chuyển hướng sau khi đăng xuất
                        .deleteCookies("JSESSIONID") // Xóa cookie sau khi đăng xuất
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/404")
                        .accessDeniedHandler(customAccessDeniedHandler()) // Xử lý Access Denied
                );
        return http.build();
    }
    @ExceptionHandler(RedirectToPasswordCreationException.class)
    public void handleRedirectToPasswordCreationException(
            RedirectToPasswordCreationException ex, HttpServletResponse response) throws IOException {
        // Redirect người dùng đến trang tạo mật khẩu mới
        response.sendRedirect(ex.getRedirectUrl());
    }

}



//package com.example.ticketbooker.Config.Security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> requests
////                        .requestMatchers("/", "/admin/**").permitAll()
////                        .anyRequest().authenticated()
//                                .anyRequest().permitAll()
//                )
//                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin((form) -> form
//                        .loginPage("/auth")
//                        .permitAll()
//                )
//                .logout((logout) -> logout
//                        .permitAll()
//                );
//
//        return http.build();
//    }
//}
