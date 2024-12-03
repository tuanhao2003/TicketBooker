
package com.example.ticketbooker.Config.Security;

import com.example.ticketbooker.Entity.Account;
import com.example.ticketbooker.Service.ServiceImp.AccountServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AccountServiceImp accountServiceImp;

    public SecurityConfig(AccountServiceImp accountServiceImp) {
        this.accountServiceImp = accountServiceImp;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();  // Define the success handler here
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(accountServiceImp)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/fuba").permitAll() // Các URL yêu cầu đăng nhập
                        .requestMatchers("/fuba/*").permitAll() // Các URL yêu cầu đăng nhập
                        .requestMatchers("/auth").permitAll()
                        .requestMatchers("/favicon.icon").permitAll()
                        .requestMatchers("/components/**").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/payment/*").permitAll()
                        .anyRequest().authenticated() // Các URL còn lại cần xác thực
//                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/auth") // Trang đăng nhập tùy chỉnh
                        .successHandler(customAuthenticationSuccessHandler())
//                        .defaultSuccessUrl("/admin/routes",true) // URL chuyển hướng sau khi đăng nhập thành công
                )
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin((form) -> form
                        .loginPage("/auth")
                        .successHandler(customAuthenticationSuccessHandler())
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout") // Đường dẫn để đăng xuất
                        .logoutSuccessUrl("/login") // Chuyển hướng sau khi đăng xuất
                        .deleteCookies("JSESSIONID") // Xóa cookie sau khi đăng xuất
                );

        return http.build();
    }

}



//package com.example.ticketbooker.Config.Security;
//
//import com.example.ticketbooker.Entity.Account;
//import com.example.ticketbooker.Service.ServiceImp.AccountServiceImp;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import java.io.IOException;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    private final AccountServiceImp accountServiceImp;
//
//    public SecurityConfig(AccountServiceImp accountServiceImp) {
//        this.accountServiceImp = accountServiceImp;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Bean
//    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
//        return new CustomAuthenticationSuccessHandler();  // Define the success handler here
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.userDetailsService(accountServiceImp)
//                .passwordEncoder(passwordEncoder());
//        return authenticationManagerBuilder.build();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/fuba").permitAll() // Các URL yêu cầu đăng nhập
//                        .requestMatchers("/fuba/*").permitAll() // Các URL yêu cầu đăng nhập
//                        .requestMatchers("/auth").permitAll()
//                        .requestMatchers("/favicon.icon").permitAll()
//                        .anyRequest().authenticated() // Các URL còn lại cần xác thực
////                        .anyRequest().permitAll()
//                )
//                .oauth2Login(oauth2 -> oauth2
//                        .loginPage("/auth") // Trang đăng nhập tùy chỉnh
//                        .successHandler(customAuthenticationSuccessHandler())
////                        .defaultSuccessUrl("/admin/routes",true) // URL chuyển hướng sau khi đăng nhập thành công
//                )
//                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin((form) -> form
//                        .loginPage("/auth")
//                        .successHandler(customAuthenticationSuccessHandler())
//                        .permitAll()
//                )
//                .logout((logout) -> logout
//                        .logoutUrl("/logout") // Đường dẫn để đăng xuất
//                        .logoutSuccessUrl("/login") // Chuyển hướng sau khi đăng xuất
//                        .deleteCookies("JSESSIONID") // Xóa cookie sau khi đăng xuất
//                );
//
//        return http.build();
//    }
//
//}



package com.example.ticketbooker.Config.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/", "/admin/**").permitAll()
//                        .anyRequest().authenticated()
                                .anyRequest().permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin((form) -> form
                        .loginPage("/auth")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .permitAll()
                );

        return http.build();
    }
}
