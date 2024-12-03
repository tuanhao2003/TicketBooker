package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Account.AccountDTO;
import com.example.ticketbooker.DTO.Ticket.TicketResponse;
import com.example.ticketbooker.DTO.Users.UpdateUserRequest;
import com.example.ticketbooker.DTO.Users.UserResponse;
import com.example.ticketbooker.Entity.Account;
import com.example.ticketbooker.Entity.CustomOAuth2User;
import com.example.ticketbooker.Entity.CustomUserDetails;
import com.example.ticketbooker.Entity.Users;
import com.example.ticketbooker.Service.AccountService;
import com.example.ticketbooker.Service.ServiceImp.CustomOAuthUserService;
import com.example.ticketbooker.Service.ServiceImp.CustomUserDetailsService;
import com.example.ticketbooker.Service.TicketService;
import com.example.ticketbooker.Service.UserService;
import com.example.ticketbooker.Util.Enum.TicketStatus;
import com.example.ticketbooker.Util.Mapper.AccountMapper;
import com.example.ticketbooker.Util.Mapper.UserMapper;
import com.example.ticketbooker.Util.SecurityUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final TicketService ticketService;
    private final UserService userService;
    private final AccountService accountService;
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomOAuthUserService customOAuthUserService;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection
    @Autowired
    public ProfileController(TicketService ticketService, AccountService accountService, UserService userService, CustomUserDetailsService customUserDetailsService, CustomOAuthUserService customOAuthUserService ,PasswordEncoder passwordEncoder) {
        this.ticketService = ticketService;
        this.accountService = accountService;
        this.userService = userService;
        this.customUserDetailsService = customUserDetailsService;
        this.customOAuthUserService = customOAuthUserService;
        this.passwordEncoder = passwordEncoder; // Inject the existing PasswordEncoder bean
    }

    @GetMapping("/info")
    public String showInfo(HttpSession session, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isLoggedIn = SecurityUtils.isLoggedIn();
        if(!isLoggedIn) {
            return  "redirect:/fuba";
        }
        Object principal = authentication.getPrincipal();
        Account account = SecurityUtils.extractAccount(principal);
        UpdateUserRequest updateUserForm = UserMapper.toUpdateDTO(account.getUser());
        model.addAttribute("email", account.getEmail());
        model.addAttribute("username", account.getUsername());
        model.addAttribute("updateUserForm", updateUserForm);
        return "View/User/Registered/Profile/Info";
    }
    @PostMapping("/info/update")
    public String updateUser(@ModelAttribute("updateUserForm") UpdateUserRequest updateUserForm, Model model,
                             @RequestParam("email") String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = SecurityUtils.extractAccount(authentication.getPrincipal());

        // Kiểm tra và cập nhật dữ liệu
        if(email != null) {
            AccountDTO accountdto = accountService.getAccountByEmail(email);
            //Nếu email không thay đổi thì ko cần cập nhật
            if(accountdto == null) {
                accountdto = accountService.getAccountById(account.getId());
                //Cập nhật email cho account
                accountdto.setEmail(email);
            }
            //Cập nhật user
            accountdto.setUser(UserMapper.fromUpdate(updateUserForm));
            //Cập nhật và lưu lại account
            Account updatedAccount =  accountService.updateAccountWithUser(accountdto);

            //Lấy lại user để cập nhật dữ liệu người đăng nhập
            CustomUserDetails updatedUserDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(updatedAccount.getUsername());
            //Cập nhật user ở client
            SecurityUtils.updateAuthentication(updatedUserDetails);
        }
        return "redirect:/profile/info";
    }

    @GetMapping("/history-booking/{accountId}")
    public String showHistoryBooking(@PathVariable int accountId,
                                     @RequestParam(required = false) Integer ticketId,
                                     @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
                                     @RequestParam(required = false) String route,
                                     @RequestParam(required = false) TicketStatus status,
                                     Model model) {
        TicketResponse ticketResponse = ticketService.searchTickets(accountId, ticketId, departureDate, route, status);
        model.addAttribute("ticketResponse", ticketResponse);
        // Lấy tất cả các giá trị của TicketStatus
        model.addAttribute("ticketStatuses", TicketStatus.values());
        return "View/User/Registered/Profile/TicketHistory";
    }

    @GetMapping("/address")
    public String showAddress() {
        return "View/User/Registered/Profile/Address";
    }

    @GetMapping("/change-password")
    public String showTicketLookup() {
        return "View/User/Registered/Profile/Password";
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestParam String newPassword, @RequestParam String oldPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isLoggedIn = SecurityUtils.isLoggedIn();
        if(!isLoggedIn) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        }
        try{
            Account account = SecurityUtils.extractAccount(authentication.getPrincipal());
            //Kiểm tra mật khẩu cũ nhập có chính xác không
            if(!account.getPassword().equals(oldPassword)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Old password is incorrect");
            }
            //Kiểm tra mật khẩu mơi có trùng mk cũ không
            if(account.getPassword().equals(newPassword)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("New password cannot be the same as the old password");
            }
            account.setPassword(newPassword);
            accountService.updateAccount(AccountMapper.toDTO(account));
            return ResponseEntity.ok("Password updated successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the password");
        }

    }

}