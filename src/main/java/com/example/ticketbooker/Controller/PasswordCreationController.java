package com.example.ticketbooker.Controller;
import com.example.ticketbooker.DTO.Account.AccountDTO;
import com.example.ticketbooker.Entity.Account;
import com.example.ticketbooker.Repository.AccountRepo;
import com.example.ticketbooker.Service.AccountService;
import com.example.ticketbooker.Util.Enum.AccountStatus;
import com.example.ticketbooker.Util.Enum.Role;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PasswordCreationController {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession httpSession;

    @Autowired
    public PasswordCreationController(AccountService accountService,PasswordEncoder passwordEncoder, HttpSession httpSession) {
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
        this.httpSession = httpSession;
    }

    @GetMapping("/new-password")
    public String showPasswordCreationPage(Model model) {
        String email = (String) httpSession.getAttribute("oauth2_email");
        String name = (String) httpSession.getAttribute("oauth2_name");
        if (email == null) {
            return "redirect:/auth";
        }
        model.addAttribute("email", email);
        model.addAttribute("name", name);
        return "View/Util/NewPassword";
    }

    @PostMapping("/new-password")
    public String handlePasswordCreation(@RequestParam String password) {
        // Lấy email từ thông tin đăng nhập
        String email = (String) httpSession.getAttribute("email");
        String username = (String) httpSession.getAttribute("username");
        System.out.println("email lay tu session" + email);
        System.out.println("username lay tu session" + username);
        System.out.println("password lay tu param" + password);
        if (email == null) {
            return "redirect:/auth";
        }
        // Tạo đối tượng tài khoản để luưu
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail(email);
        accountDTO.setUsername(username);
        accountDTO.setPassword(password);
        accountDTO.setRole(Role.valueOf("CUSTOMER"));
        accountDTO.setAccountStatus(AccountStatus.ACTIVE);
        System.out.println("Account dto tao tk sau khi nhap mk" + accountDTO);
        accountService.createAccount(accountDTO);

        // Mã hóa mật khẩu mới và lưu vào cơ sở dữ liệu
//        account.setPassword(new BCryptPasswordEncoder().encode(password));
//        accountRepo.save(account);

        // Clear session data
        httpSession.removeAttribute("oauth2_email");
        httpSession.removeAttribute("oauth2_name");

        // Sau khi tạo mật khẩu mới, chuyển hướng người dùng đến trang chính
        return "redirect:/fuba";
    }
}