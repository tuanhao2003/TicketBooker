package com.example.ticketbooker.Controller.Api;

import com.example.ticketbooker.DTO.Account.AccountDTO;
import com.example.ticketbooker.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountApi {

    @Autowired
    private AccountService accountService;

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
    @GetMapping("exist")
    public ResponseEntity<?> existAccount(@RequestParam(name = "email", required = true) String email) {
        if (accountService.getAccountByEmail(email) == null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
