package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Account.LoginRequestDTO;
import com.example.ticketbooker.Service.ServiceImp.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AccountService accountService;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequest) {
        boolean valid;
        try{
            valid = accountService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        };
        return ResponseEntity.ok("Login successful!");
    }
}
