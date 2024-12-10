package com.example.ticketbooker.Controller;

import com.example.ticketbooker.DTO.Account.AccountDTO;
import com.example.ticketbooker.Service.AccountService;
import com.example.ticketbooker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Controller
@RequestMapping("/admin/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    //Hiển thị danh sách accounts
    @GetMapping()
    public String listAccounts(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<AccountDTO> accountPage = accountService.getAllAccounts(pageable);

        model.addAttribute("accounts", accountPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", accountPage.getTotalPages());

        return "View/Admin/AccountManagement/Account";
    }

    //Hiển thị form thêm account
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("accountDTO", new AccountDTO());
        model.addAttribute("users", userService.getAllUsers().getListUsers()); // Lấy danh sách user
        return "View/Admin/AccountManagement/AccountForm";
    }

    // Xử lý thêm account
    @PostMapping("/add")
    public String addAccount(@ModelAttribute("accountDTO") AccountDTO accountDTO, RedirectAttributes redirectAttributes) {
        accountService.createAccount(accountDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm tài khoản thành công!");
        return "redirect:/admin/accounts";
    }

    // Hiển thị form sửa account
    @GetMapping("/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        AccountDTO accountDTO = accountService.getAccountById(id);
        if (accountDTO != null) {
            model.addAttribute("accountDTO", accountDTO);
            model.addAttribute("users", userService.getAllUsers().getListUsers()); // Lấy danh sách user
            return "View/Admin/AccountManagement/AccountForm";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy tài khoản với ID: " + id);
            return "redirect:/admin/accounts";
        }
    }

    // Xử lý sửa account
    @PostMapping("/update")
    public String updateAccount(@ModelAttribute("accountDTO") AccountDTO accountDTO, RedirectAttributes redirectAttributes) {
        boolean updatedAccount = accountService.updateAccount(accountDTO);
        if (updatedAccount) {
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật tài khoản thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Cập nhật tài khoản thất bại!");
        }
        return "redirect:/admin/accounts";
    }

    @GetMapping("/search")
    public String searchAccounts(Model model, @RequestParam("keyword") String keyword) {
        List<AccountDTO> accounts = accountService.searchAccounts(keyword);
        model.addAttribute("accounts", accounts);
        // Add other attributes needed for the view if any (e.g., currentPage, totalPages)
        return "View/Admin/AccountManagement/Account"; // Return the same view as the listAccounts method
    }
}
