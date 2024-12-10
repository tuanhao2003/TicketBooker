package com.example.ticketbooker.Controller.Api;

import com.example.ticketbooker.DTO.Account.AccountDTO;
import com.example.ticketbooker.DTO.Account.ResetPasswordRequest;
import com.example.ticketbooker.Service.AccountService;
import com.example.ticketbooker.Service.OutSource.EmailService;
import com.example.ticketbooker.Util.Mapper.AccountMapper;
import com.example.ticketbooker.Util.Utils.NetworkInterfaceUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

import java.net.SocketException;

@RestController
@RequestMapping("/api/accounts")
public class AccountApi {

    @Autowired
    private AccountService accountService;
    @Autowired
    private EmailService emailService;

    //Xóa account
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable("id") Integer id) {
        if (accountService.getAccountById(id) != null) {
            accountService.deleteAccount(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Cập nhật trạng thái account
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateAccountStatus(@PathVariable("id") Integer id, @RequestBody AccountDTO accountDTO) {
        AccountDTO existingAccount = accountService.getAccountById(id);
        if (existingAccount != null) {
            existingAccount.setAccountStatus(accountDTO.getAccountStatus()); // Update only the status
            accountService.updateAccount(existingAccount);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Cập nhật vai trò account
    @PatchMapping("/{id}/role")
    public ResponseEntity<?> updateAccountRole(@PathVariable("id") Integer id, @RequestBody AccountDTO accountDTO) {
        AccountDTO existingAccount = accountService.getAccountById(id);
        if (existingAccount != null) {
            existingAccount.setRole(accountDTO.getRole()); // Update only the role
            accountService.updateAccount(existingAccount);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Khai tìm account tồn tại
    @GetMapping("/exist")
    public ResponseEntity<?> existAccount(
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "username", required = false) String username) {

        if ((email != null && accountService.getAccountByEmail(email) != null) ||
            (username != null && accountService.getAccountByUsername(username) != null)) {
            System.out.println("Timf thay");
            return ResponseEntity.ok().build();
        }
        System.out.println("Khoong tim thay: ");
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/confirm-reset")
    public boolean resetPassword(@RequestBody ResetPasswordRequest request) {
        Context context = new Context();
        AccountDTO resettingAccount = accountService.getAccountByUsername(request.getUsername());
        String currentIp = "";
        try {
            currentIp = NetworkInterfaceUtils.getIPv4Address();
        } catch (Exception e) {
            e.printStackTrace();
        }
//      vì đang là chạy localhost nên sẽ để url là ip của thiết bị server
        String requestUrl = "http://"+currentIp+":8080/api/accounts/reset-password?accountId=" + resettingAccount.getId()+"&newPassword="+request.getNewPassword();
        context.setVariable("resetLink", requestUrl);
        return emailService.sendEmail(resettingAccount.getEmail(), "Reset Password Confirm", "EmailTemplate/ResetPassword", context);
    }

    @GetMapping("/reset-password")
    public void resetPassword(@RequestParam int accountId, @RequestParam String newPassword, HttpServletResponse response) {
        try {
            AccountDTO accountDTO = accountService.getAccountById(accountId);
            accountDTO.setPassword(newPassword);
            accountService.updateAccount(accountDTO);
            response.sendRedirect("/auth");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
