package com.example.ticketbooker.Controller;

import com.example.ticketbooker.Service.ServiceImp.AccountServiceImp;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AccountServiceImp accountServiceImp;
//    @PostMapping("/")
//    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequest) {
//        boolean valid;
//        try{
//            valid = accountService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        };
//        return ResponseEntity.ok("Login successful!");
//    }

    @GetMapping("/profile")
    public String getUserProfile(HttpSession session,Model model) {
        String username = (String) session.getAttribute("username"); // Giả sử bạn đã lưu tên người dùng vào session
        System.out.println(username);
        model.addAttribute("name", username);
        // Trả về thông tin người dùng hoặc giao diện
        return "View/User/Profile"; // Trả về view tên là "profile"
    }

    @GetMapping()
    public String login() {
        return "View/Util/Login"; // Trả về trang login.html
    }
}
