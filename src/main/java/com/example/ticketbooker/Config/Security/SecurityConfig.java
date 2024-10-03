package com.example.ticketbooker.Config.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests ) -> requests
                        .requestMatchers("/","/driver/*","/driver", "/profile").permitAll()
                        .anyRequest().authenticated()
                );
//                .formLogin((form) -> form
//                        .loginPage("/Authentication")
//                        .permitAll()
//                )
//                .logout((logout) -> logout
//                        .permitAll()
//                );

        return http.build();
    }
}
